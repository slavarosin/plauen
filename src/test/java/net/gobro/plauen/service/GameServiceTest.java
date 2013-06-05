package net.gobro.plauen.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.Image;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.model.Present;
import net.gobro.plauen.model.Text;
import net.gobro.plauen.model.TextCodeEnum;
import net.gobro.plauen.service.GameService;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet
public class GameServiceTest extends BaseDatabaseTest {
	@SpringBeanByType
	private GameService gameService;

	@Test
	public void testFetch() {
		Game obj = gameService.fetch(1);
		Assert.assertNotNull(obj);
		Assert.assertEquals(1, obj.getId().intValue());
		Assert.assertEquals(CountryEnum.EE, obj.getCountry());
		Assert.assertNotNull(obj.getPresent());
		Assert.assertEquals(1, obj.getPresent().getId().intValue());
		Assert.assertNotNull(obj.getPresent().getTexts());
		Assert.assertEquals(2, obj.getPresent().getTexts().size());
	}

	@Test
	public void testCreate() throws IOException {
		// prepare game to test
		Game newGame = new Game();
		newGame.setCountry(CountryEnum.EE);
		newGame.setActivateAt(Calendar.getInstance());
		newGame.setStartedAt(Calendar.getInstance());
		newGame.setPresent(new Present());
		newGame.getPresent().setComment("no comments");
		newGame.getPresent().setName("no name");
		newGame.getPresent().setPrice(BigDecimal.valueOf(123.33));
		newGame.getPresent().setTexts(new ArrayList<Text>());

		Text text = new Text();
		text.setCode(TextCodeEnum.NAME);
		text.setLanguage(LanguageEnum.et);
		text.setPresent(newGame.getPresent());
		text.setText("toote nimi");

		newGame.getPresent().getTexts().add(text);

		text = new Text();
		text.setCode(TextCodeEnum.DESCRIPTION);
		text.setLanguage(LanguageEnum.et);
		text.setPresent(newGame.getPresent());
		text.setText("kirjeldus");

		newGame.getPresent().getTexts().add(text);
		text = new Text();
		text.setCode(TextCodeEnum.NAME);
		text.setLanguage(LanguageEnum.en);
		text.setPresent(newGame.getPresent());
		text.setText("title");

		newGame.getPresent().getTexts().add(text);

		text = new Text();
		text.setCode(TextCodeEnum.DESCRIPTION);
		text.setLanguage(LanguageEnum.en);
		text.setPresent(newGame.getPresent());
		text.setText("description");

		newGame.getPresent().getTexts().add(text);

		// add image
		newGame.getPresent().setImages(new ArrayList<Image>());

		ClassPathResource testImage;
		Image image;

		testImage = new ClassPathResource("test-image-01.gif", getClass());

		image = new Image();
		image.setImage(IOUtils.toByteArray(testImage.getInputStream()));
		image.setPresent(newGame.getPresent());
		newGame.getPresent().getImages().add(image);

		testImage = new ClassPathResource("test-image-02.jpg", getClass());

		image = new Image();
		image.setImage(IOUtils.toByteArray(testImage.getInputStream()));
		image.setPresent(newGame.getPresent());
		newGame.getPresent().getImages().add(image);

		// store
		Game obj = gameService.create(newGame);

		// test
		Assert.assertNotNull(obj);
		Assert.assertNotNull(obj.getId());
		Assert.assertNotNull(obj.getPresent().getId());
		Assert.assertNotNull(obj.getPresent().getTexts());

		for (Text t : obj.getPresent().getTexts()) {
			Assert.assertNotNull(t.getId());
		}
	}
}
