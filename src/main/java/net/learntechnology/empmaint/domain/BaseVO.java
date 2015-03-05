package net.learntechnology.empmaint.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class BaseVO implements Serializable {
	private static final long serialVersionUID = 2061856554442918007L;

	@Override
	public String toString() {
		 return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
