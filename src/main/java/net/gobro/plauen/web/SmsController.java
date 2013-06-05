package net.gobro.plauen.web;

import java.math.BigDecimal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.CurrencyEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.model.SMSRejectedException;
import net.gobro.plauen.model.ScoreRule;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.StatusEnum;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.ScoreService;
import net.gobro.plauen.service.SmsService;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.web.spring.SmsStatusEnumEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * http://localhost:8080/plauen/do/sms?sender=3725131010&country=EE&currency=EEK
 * &service_id=22463760dca366bc034545fb0fade345&message_id=1&keyword=FOR%20
 * PLAYER
 * &shortcode=1311&status=pending&price=5&sig=28b01b849508aa28447efcb89f8eb62b
 * &message=B00000
 */
@Controller
public class SmsController {
	private static final Logger LOG = LoggerFactory
			.getLogger(SmsController.class);
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private SupportedLanguagesService supportedLanguagesService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private GameService gameService;

	/**
	 * @param model
	 * @param message
	 *            Sõnumi sisust märksõnad maha arvata. Näiteks, kui sõnum on TXT
	 *            KEY 123, on see parameeter 123. Parameeter jääb tühjaks, kui
	 *            on olemas ainult märksõna ja ei ole lisateksti sõnumis.
	 * @param sender
	 *            Sõnumisaatja telefoninumber rahvusvahelises formaadis ilma
	 *            plussmärgita. Näiteks, 4560123456 või 358401234567
	 * @param country
	 *            Saatja mobiilioperaatori riigikood. Lähtuvalt ISO 3166-1
	 *            standardist kasutatakse kahetähelisi koode (SE- Rootsi, FI -
	 *            Soome, NO - Norra, LT - Leedu, LV - Läti, EE - Eesti jne).
	 *            Palun pane tähele, et see EI pruugi ilmtingimata olla saatja
	 *            tegelik asukoht.
	 * @param price
	 *            Lõppkasutaja sõnumi hind kohalikus valuutas, k.a. käibemaks.
	 * @param currency
	 *            Kohaliku valuuta sümbol lähtuvalt ISO 4217-le (EUR, SEK, NOK,
	 *            DKK, LTL, LVL, EEK, USD, GBP jne).
	 * @param service_id
	 *            String, mis identifitseerib Sinu teenuse. Näiteks
	 *            f7fa12b381d290e268f99e382578d64a. String muutub vajalikuks
	 *            siis kui Sul on mitu teenust sama URL-ga, sellisel juhul saad
	 *            oma teenuseid just selle stringi abil eristada.
	 * @param message_id
	 *            String, mis on unikaalne iga sõnumi kohta, mille su teenus
	 *            vastu võtab.
	 * @param keyword
	 *            Sõnumi märksõna osa. Näiteks, kui sõnum on TXT KEY 123, on see
	 *            parameeter TXT KEY.
	 * @param shortCode
	 *            Lühinumber, millele sõnum saadeti.
	 * @param status
	 *            Billing status, which is either PENDING, OK or FAILED.
	 * @return
	 */
	@RequestMapping(value = "/sms", method = RequestMethod.GET)
	public String add(ModelMap model, @RequestParam("message") String message,
			@RequestParam("sender") String sender,
			@RequestParam("country") CountryEnum country,
			@RequestParam("price") BigDecimal price,
			@RequestParam("currency") CurrencyEnum currency,
			@RequestParam("service_id") String serviceId,
			@RequestParam("message_id") String messageId,
			@RequestParam("keyword") String keyword,
			@RequestParam("shortcode") String shortCode,
			@RequestParam("status") StatusEnum status) {
		Sms sms = new Sms();
		sms.setCountry(country);
		sms.setCurrency(currency);
		sms.setKeyword(keyword);
		sms.setMessage(message);
		sms.setMessageId(messageId);
		sms.setPrice(price);
		sms.setSender(sender);
		sms.setServiceId(serviceId);
		sms.setShortCode(shortCode);
		sms.setStatus(status);

		model.addAttribute("sender", sender);
		model.addAttribute("message", message);

		// find the language of the sender's countrys
		LanguageEnum language = this.supportedLanguagesService
				.getDefaultLanguageFor(country);

		if (language == null) {
			if (LOG.isWarnEnabled()) {
				LOG
						.warn("The country ["
								+ country
								+ "] is not found in the list of supported countries. The response for incoming SMS will be in english.");

			}

			language = LanguageEnum.en;
		}

		model.addAttribute("countryLocale", language.name());

		try {
			smsService.store(sms, true);

			// check if game is opened for bidding
			Game game = gameService.fetch(sms.getUserPlay().getGame().getId());

			if (game.isBiddingOpened()) {
				model.addAttribute("messageKey", "sms.successful");
			} else if (game.isGameClosed()) {
				model.addAttribute("messageKey", "sms.error.game-closed");
			} else {
				model.addAttribute("messageKey", "sms.error.bidding-closed");
			}
		} catch (SMSRejectedException e) {
			model.addAttribute("messageKey", e.getMessage());
		}

		return "sms";
	}

	@RequestMapping(value = "/sms/bot", method = RequestMethod.GET)
	public String addBotMessage(ModelMap model, HttpServletRequest request,
			@RequestParam("message") String message,
			@RequestParam("gameId") Integer gameId,
			@RequestParam("scoreRuleId") Integer scoreRuleId) {

		ScoreRule scoreRule = getScoreService().fetch(scoreRuleId);
		Sms sms = smsService.createFakeSms(message, scoreRule);
		smsService.store(sms, false);

		return "redirect:/do/game/view?id=" + gameId;
	}

	@InitBinder
	public void init(WebDataBinder binder, Locale locale) {
		binder
				.registerCustomEditor(StatusEnum.class,
						new SmsStatusEnumEditor());
	}

	public ScoreService getScoreService() {
		return (ScoreService) applicationContext.getBean("scoreService");
	}
}
