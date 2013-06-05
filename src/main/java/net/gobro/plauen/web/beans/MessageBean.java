package net.gobro.plauen.web.beans;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

@SuppressWarnings("unchecked")
public class MessageBean {

	private List<String> text = LazyList.decorate(new LinkedList(),
			FactoryUtils.instantiateFactory(String.class));

	private List<LanguageBean> languages;

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	public List<LanguageBean> getLanguages() {
		return languages;
	}

	public void setLanguages(List<LanguageBean> languages) {
		this.languages = languages;
	}

}
