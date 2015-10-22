package ru.backup.domain;

/**
 * 
 * 
 * 
 * @author Roman
 *
 */
public enum ApplicationUrl {

	FilesUrl(".");

	private String url;

	private ApplicationUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
