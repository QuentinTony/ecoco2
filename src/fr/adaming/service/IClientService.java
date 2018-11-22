package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientService {

	public Client addClient(Client cl);

	public int deleteClient(Client cl);

	public int updateClient(Client cl);

	public Client getClient(Client cl);
	
	public Client isExist(Client cl);


}
