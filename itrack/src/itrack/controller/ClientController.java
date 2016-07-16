package itrack.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import itrack.controller.ControllerConfig;
import itrack.dao.impl.Response;
import itrack.pageModel.Json;
import itrack.pageModel.SessionInfo;
import itrack.service.JSClientService;

import com.sun.xml.internal.ws.policy.sourcemodel.ModelNode;


@Controller
@RequestMapping("/clientController")
public class ClientController {
	
	@Autowired
	private JSClientService jsClientService;
	
	@ResponseBody
	@RequestMapping("/SearchJSClientP/mobile")
	public Json SearchJSClientP(String pinyin){
		Response response = new Response();
		try {
			response = jsClientService.searchClientByPingyin(pinyin);
		} catch (Exception e){
			response.setFail(e.getMessage());
		}
		
		Json json = new Json(response);
		
		return json;
	}
}
