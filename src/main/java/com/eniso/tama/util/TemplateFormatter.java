package com.eniso.tama.util;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TemplateFormatter {

	private static final Pattern DOL=Pattern.compile("[$][{](?<v>[^}]+)[}]");	
	public static String format(String s,Map<String,String> vars) {
			return format(s, x->vars.get(x));
	}
	
	public static String format(String s,Function<String,String> vars) {
		StringBuffer  sb=new StringBuffer();
		Matcher m=DOL.matcher(s);
		while(m.find()) {
			String v=m.group("v");
			String a=vars.apply(v);
			if(a==null) {
				a=m.group();
			}
			//m.appendReplacement(sb, Pattern.quote(a));
			m.appendReplacement(sb, a);
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	
}
