package client.controller.responseController;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import client.view.RightGameInfoBoard;
import xml.Message;

public class FindWordResponseController extends ControllerChain {

	public Application app;
	public Model model;
	
	public FindWordResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}
	
	@Override
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals ("findWordResponse")) {
			return next.process(response);
		}
		
		
		Node boardResponse = response.contents.getFirstChild();
		NamedNodeMap map = boardResponse.getAttributes();
		
		String gameId = map.getNamedItem("gameId").getNodeValue();
		String score = map.getNamedItem("score").getNodeValue();
		String pname = map.getNamedItem("name").getNodeValue();
		
		app.getResponseArea().append("Board Message received for game:" + boardResponse.toString() + "\n");
		model.getPlayer().setScore(Integer.valueOf(score));

			((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
			((RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel()).updateGameInfoBoard();


		return true;
	}
}
