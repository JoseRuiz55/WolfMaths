package com.uma.wolfmaths.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.wolfmaths.constants.WolframAlphaConstants;
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class WolframAlphaService {
	
	private static String appid = "9Y54L8-UT7RTJ9JRG";
	private static final Logger logger = LoggerFactory.getLogger(WolframAlphaService.class);
	
	public static String getWolframResult(String queryToExecute) {

        // Use "pi" as the default query, or caller can supply it as the lone command-line argument.
        String input = "pi";
        String result = "";
        //if (args.length > 0)
        //    input = args[0];
        logger.info("1");
        // The WAEngine is a factory for creating WAQuery objects,
        // and it also used to perform those queries. You can set properties of
        // the WAEngine (such as the desired API output format types) that will
        // be inherited by all WAQuery objects created from it. Most applications
        // will only need to crete one WAEngine object, which is used throughout
        // the life of the application.
        WAEngine engine = new WAEngine();
        
        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID(appid);
        engine.addFormat("plaintext");
        logger.info("2");
        // Create the query.
        WAQuery query = engine.createQuery();
        logger.info("3");
        // Set properties of the query.
        query.setInput(queryToExecute);
        logger.info("4");
        try {
            // For educational purposes, print out the URL we are about to send:
            logger.info("Query URL:");
            logger.info(engine.toURL(query));
            logger.info("");
            
            // This sends the URL to the Wolfram|Alpha server, gets the XML result
            // and parses it into an object hierarchy held by the WAQueryResult object.
            WAQueryResult queryResult = engine.performQuery(query);
            
            if (queryResult.isError()) {
                logger.info("Query error");
                logger.info("  error code: " + queryResult.getErrorCode());
                logger.info("  error message: " + queryResult.getErrorMessage());
            } else if (!queryResult.isSuccess()) {
                logger.info("Query was not understood; no results available.");
            } else {
                // Got a result.
                logger.info("Successful query. Pods follow:\n");
                for (WAPod pod : queryResult.getPods()) {
                	logger.info(pod.getTitle());
                	//logger.info(WolframAlphaConstants.POD_DECIMAL_APROXIMATION);
                    if (!pod.isError() && (pod.getTitle().equals(WolframAlphaConstants.POD_DECIMAL_APROXIMATION) || pod.getTitle().equals(WolframAlphaConstants.POD_RESULT) || pod.getTitle().equals(WolframAlphaConstants.POD_REAL_SOLUTION) )) {
                        logger.info(pod.getTitle());
                        logger.info("------------");
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText) {
                                    logger.info(((WAPlainText) element).getText());
                                    logger.info("");
                                    result = (((WAPlainText) element).getText());
                                }
                            }
                        }
                        logger.info("");
                    }
                }
                // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
                // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
            }
        } catch (WAException e) {
            logger.info(e.getMessage());
        }
		return result;
    }

}
