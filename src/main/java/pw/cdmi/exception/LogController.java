package pw.cdmi.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.LoggerContext;

@Controller
public class LogController {
	private static final Logger logger = LoggerFactory.getLogger(LogController.class);
	
	@RequestMapping(value="/log/{level}", method = RequestMethod.GET)
	public void changeLogLevel(@PathVariable String level){
//		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//		ch.qos.logback.classic.Logger log = lc.getLogger(Logger.ROOT_LOGGER_NAME);
//		
//		logger.info("info: current log level is [" + log.getLevel().toString() + "]");
//		ch.qos.logback.classic.Level l = Level.toLevel(level);
//		log.setLevel(l);
//		logger.debug("debug: log level changed, now is [" + l.toString() + "]");
	}
}
