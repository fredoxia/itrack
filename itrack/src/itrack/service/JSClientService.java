package itrack.service;

import java.util.ArrayList;
import java.util.List;

import itrack.dao.VO.ClientJinSuanVO;
import itrack.dao.entity.SQLServer.ClientsMS;
import itrack.dao.impl.Response;
import itrack.dao.impl.SQLServer.ClientDAOImpl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JSClientService {
	
	@Autowired
	private ClientDAOImpl clientDAOImpl;

	public Response searchClientByPingyin(String pinyin) {
		Response response = new Response();
		
		if (pinyin != null && !pinyin.trim().equals("")){
			pinyin = pinyin.replaceAll(" ", "_");

			List<ClientsMS> clients = clientDAOImpl.getClientByPinyin(pinyin);

			List<ClientJinSuanVO> clientVos = transferClients(clients);
			response.setReturnValue(clientVos);
		} else {
			response.setFail("请输入至少两个字符搜索客户信息");
		}
		
		return response;
	}
	
	private List<ClientJinSuanVO> transferClients(List<ClientsMS> clients){
		List<ClientJinSuanVO> clientVO = new ArrayList<>();
		if (clients != null && clients.size() > 0){
			for (ClientsMS client : clients){
				String clientName = client.getName();
				
				if (clientName.indexOf("员工") != -1 ||clientName.indexOf("三楼") != -1 || clientName.indexOf("不做") != -1 || clientName.indexOf("不用") != -1){
				    System.out.println(clientName + "," + client.getRegion().getName());
					continue;
				}
				
				ClientJinSuanVO clientJinSuanVO = new ClientJinSuanVO(client);
				clientVO.add(clientJinSuanVO);
			}
		}
		
		return clientVO;
	}

}
