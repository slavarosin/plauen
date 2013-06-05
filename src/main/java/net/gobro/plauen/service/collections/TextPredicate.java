package net.gobro.plauen.service.collections;

import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.model.Text;
import net.gobro.plauen.model.TextCodeEnum;

import org.apache.commons.collections.Predicate;


public class TextPredicate implements Predicate {
	private TextCodeEnum code;
	private LanguageEnum language;

	public TextPredicate(TextCodeEnum code, LanguageEnum language) {
		this.code = code;
		this.language = language;
	}

	@Override
	public boolean evaluate(Object obj) {
		Text text = (Text) obj;
		return code.equals(text.getCode())
				&& language.equals(text.getLanguage());
	}

}