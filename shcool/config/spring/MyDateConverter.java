package spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;



public class MyDateConverter implements Converter<String, Date> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");// 小写的mm表示的是分钟
    Date date = new Date();

    @Override
    public Date convert(String source) {
        try {
            date = sdf.parse(source);
        } catch (ParseException e) {
            logger.error("数据转换发生了异常,要转化的数据{}",source);
            e.printStackTrace();
        }
        return date;
    }
	

}
