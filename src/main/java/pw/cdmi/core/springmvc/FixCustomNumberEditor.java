package pw.cdmi.core.springmvc;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

/**
 * **********************************************************
 * 该类是为了解决Spring MVC将页面数据封装为对象时候，当页面数据值为null或""时不能完成绑定的问题.
 * 在使用时候，需要在Controller类上增加@InitBinder注解
 * 
 * @author wuwei
 * @version iSoc Service Platform, 2015年8月26日
 ***********************************************************
 */
public class FixCustomNumberEditor extends PropertyEditorSupport {

	private final Class<? extends Number> numberClass;

	private final NumberFormat numberFormat;

	private final boolean allowEmpty;

	/**
	 * Create a new CustomNumberEditor instance, using the default
	 * {@code valueOf} methods for parsing and {@code toString}
	 * methods for rendering.
	 * <p>The "allowEmpty" parameter states if an empty String should
	 * be allowed for parsing, i.e. get interpreted as {@code null} value.
	 * Else, an IllegalArgumentException gets thrown in that case.
	 * @param numberClass Number subclass to generate
	 * @param allowEmpty if empty strings should be allowed
	 * @throws IllegalArgumentException if an invalid numberClass has been specified
	 * @see org.springframework.util.NumberUtils#parseNumber(String, Class)
	 * @see Integer#valueOf
	 * @see Integer#toString
	 */
	public FixCustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) throws IllegalArgumentException {
		this(numberClass, null, allowEmpty);
	}

	/**
	 * Create a new CustomNumberEditor instance, using the given NumberFormat
	 * for parsing and rendering.
	 * <p>The allowEmpty parameter states if an empty String should
	 * be allowed for parsing, i.e. get interpreted as {@code null} value.
	 * Else, an IllegalArgumentException gets thrown in that case.
	 * @param numberClass Number subclass to generate
	 * @param numberFormat NumberFormat to use for parsing and rendering
	 * @param allowEmpty if empty strings should be allowed
	 * @throws IllegalArgumentException if an invalid numberClass has been specified
	 * @see org.springframework.util.NumberUtils#parseNumber(String, Class, java.text.NumberFormat)
	 * @see java.text.NumberFormat#parse
	 * @see java.text.NumberFormat#format
	 */
	public FixCustomNumberEditor(Class<? extends Number> numberClass,
			NumberFormat numberFormat, boolean allowEmpty) throws IllegalArgumentException {

		if (numberClass == null || !Number.class.isAssignableFrom(numberClass)) {
			throw new IllegalArgumentException("Property class must be a subclass of Number");
		}
		this.numberClass = numberClass;
		this.numberFormat = numberFormat;
		this.allowEmpty = allowEmpty;
	}


	/**
	 * Parse the Number from the given text, using the specified NumberFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
//			setValue(null);
			return;
		}
		else if (this.numberFormat != null) {
			// Use given NumberFormat for parsing text.
			setValue(NumberUtils.parseNumber(text, this.numberClass, this.numberFormat));
		}
		else {
			// Use default valueOf methods for parsing text.
			setValue(NumberUtils.parseNumber(text, this.numberClass));
		}
	}

	/**
	 * Coerce a Number value into the required target class, if necessary.
	 */
	@Override
	public void setValue(Object value) {
		if (value instanceof Number) {
			super.setValue(NumberUtils.convertNumberToTargetClass((Number) value, this.numberClass));
		}
		else {
			super.setValue(value);
		}
	}

	/**
	 * Format the Number as String, using the specified NumberFormat.
	 */
	@Override
	public String getAsText() {
		Object value = getValue();
		if (value == null) {
			return "";
		}
		if (this.numberFormat != null) {
			// Use NumberFormat for rendering value.
			return this.numberFormat.format(value);
		}
		else {
			// Use toString method for rendering value.
			return value.toString();
		}
	}

}
