package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService {

	// association UML
	@EJB
	private IClientDao clDao;

	@Override
	public Client addClient(Client cl) {

		return clDao.addClient(cl);
	}

	@Override
	public int deleteClient(Client cl) {

		Client clOut = this.getClient(cl);

		return clDao.deleteClient(clOut);

	}

	@Override
	public int updateClient(Client cl) {
		
		Client clOut = this.getClient(cl);
		clOut.setId(cl.getId());
		clOut.setAdresse(cl.getAdresse());
		clOut.setListeCommandes(cl.getListeCommandes());
		clOut.setMail(cl.getMail());
		clOut.setMdp(cl.getMdp());
		clOut.setNom(cl.getNom());
		clOut.setTel(cl.getTel());
		
		return clDao.updateClient(clOut);
	}

	@Override
	public Client getClient(Client cl) {

		return clDao.getClient(cl);
	}

	@Override
	public Client isExist(Client cl) {
		// TODO Auto-generated method stub
		return clDao.isExist(cl);
	}

}
