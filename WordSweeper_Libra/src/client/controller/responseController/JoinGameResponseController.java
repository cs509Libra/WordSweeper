package client.controller.responseController;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import client.model.Model;
import client.view.Application;
import client.view.LeftBoardPanel;
import client.view.RightGameInfoBoard;
import xml.Message;

/**
 * A join game response information sent from server should be received once a player is trying to join into an existing server.
 * 
 * The {@link #process(Message)} handles the join game response information. 
 * If the connection is successful, the corresponding information of entity classes will be updated, and the GUI in boundary classes will be changed to the game part from login.
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */

public class JoinGameResponseController extends ControllerChain {
	public Application app;
	public Model model;

	public JoinGameResponseController(Application a, Model m) {
		super();
		this.app = a;
		this.model = m;
	}

	@Override
	public boolean process(Message response) {

		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("joinGameResponse")) {
			return next.process(response);
		}

		if (response.contents.getAttributes().getNamedItem("success").getNodeValue().equals("false")) {
			app.setError_messege(response.contents.getAttributes().getNamedItem("reason").getNodeValue());
			return false;
		} else
			this.model.existedGame = true;
		Node boardResponse = response.contents.getFirstChild();
		NamedNodeMap map = boardResponse.getAttributes();
		String gameId = map.getNamedItem("gameId").getNodeValue();
		model.getGame().setGameID(gameId);

		((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
		((RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel()).updateGameInfoBoard();

		return true;
	}

}
