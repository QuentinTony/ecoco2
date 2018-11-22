package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService{

	// association UML
	@EJB
	private IClientDao clDao;
	
	@Override
	public Client addClient(Client cl) {
		
		return clDao.addClient(cl);
	}

	@Override
	public int deleteClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client getClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

}
