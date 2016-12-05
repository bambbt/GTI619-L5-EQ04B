package com.gti619.service;	

import java.security.SecureRandom;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gti619.config.PasswordEncoder;
import com.gti619.daos.MatriceUserHome;
import com.gti619.daos.RoleHome;
import com.gti619.daos.UserHome;
import com.gti619.model.MatriceUser;
import com.gti619.model.Role;
import com.gti619.model.User;

@Service("userService")
public class UserService {

	public static final int NBVALEUR_MATRICE = 25;
	public static final int VALEUR_MATRICE_MAX = 100;
	@Autowired
	private UserHome userDao;
	@Autowired
	private MatriceUserHome matriceDao;
	@Autowired
	private RoleHome roleDao;
	@Autowired
	@Qualifier("oldPasswordService")
	private OldPasswordService oldPassService;

	@Autowired
	@Qualifier("securityConfigService")
	private SecurityConfigService configService;


	public boolean validatePasswd(String username, String userPass) {

		String strSalt = userDao.findByUserName(username).getSalt();

		//ajout du sel au le mot de pass
		String mixedPass = strSalt+userPass;

		//encodage du password
		String passHash = null;
		try {
			passHash = PasswordEncoder.MD5encrypt(mixedPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User user = userDao.findByUserName(username);
		if(user.getMdp().contentEquals(passHash))
			return true;
		else
			return false;
	}

	public void addUser(String strRole, String login, String completeName, String mail, String password) {

		//Creation du salt
		SecureRandom random = new SecureRandom();
		int salt = random.nextInt();

		String strSalt = Integer.toString(salt);

		//ajout du sel au le mot de pass
		String mixedPass = strSalt+password;



		//encodage du password
		String passHash = null;
		try {
			passHash = PasswordEncoder.MD5encrypt(mixedPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		User user = new User();

		user.setEmail(mail);
		user.setLogin(login);
		user.setMdp(passHash);
		user.setSalt(strSalt);
		user.setName(completeName);
		user.setIsLocked(0);
		user.setNbTentativesConn(0);
		Role role= roleDao.findByName(strRole);
		user.setRole(role);
		userDao.persist(user);
		
		
		MatriceUser matrice = null;
		if(user.getRole().getNom().toString().contentEquals("ADMIN")){
			matrice = new MatriceUser();
			matrice.setUser(user);
			String strMatrice = "";
			SecureRandom rand = new SecureRandom();
			for (int i = 0; i < NBVALEUR_MATRICE; i++) {
				int value = rand.nextInt(VALEUR_MATRICE_MAX);
				strMatrice +=  String.valueOf(value)+",";
				
			}
			matrice.setValue(strMatrice);	
			matriceDao.persist(matrice);
		}

		
		oldPassService.saveOldPass(user);
	}

	public boolean oldPasswordCheckUsed(String principal, String password){

		String strSalt = userDao.findByUserName(principal).getSalt();

		String mixedPass = strSalt+password;


		//encodage du password
		String passHash = PasswordEncoder.MD5encrypt(mixedPass);


		return (userDao.lookForSamePass(principal,passHash) != 0);
	}

	public void changePassword(String login, String password) { 

		//
		String strSalt = userDao.findByUserName(login).getSalt();

		//ajout du sel au le mot de pass
		String mixedPass = strSalt+password;

		//encodage du password
		String passHash = PasswordEncoder.MD5encrypt(mixedPass);

		User user = userDao.findByUserName(login);



		oldPassService.saveOldPass(user);		
		user.setMdp(passHash);

		oldPassService.saveOldPass(user);

		userDao.attachDirty(user);
	}



	public User findBylogin (String login){
		return userDao.findByUserName(login);
	}



	/**
	 * Permet de v�rifier si un utilisateur est pr�sent dans la base de donn�e
	 * lors de la demande de recovery mdp
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	public boolean userExist(String login) {

		return (userDao.checkIfUsernameExist(login));
	}



	/**
	 * Permet de g�n�rer un id recovery aleatoire et au compte utilisateur
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	public void setRecoveryId(String login) {

		User user = userDao.findByUserName(login);
		int value  = new SecureRandom().nextInt(999999999);
		user.setRecoveryId(String.valueOf(value));

		userDao.attachDirty(user);

	}

	public boolean recoveryValide(String username, String recoveryValide) {

		User user = userDao.findByUserName(username);

		return (user.getRecoveryId().toString().contentEquals(recoveryValide));

	}

	public void resetRecoveryId(String username) {
		User user = userDao.findByUserName(username);

		user.setRecoveryId(new String("0"));

		userDao.attachDirty(user);


	}

	public void attachDirty(User user) {
		userDao.attachDirty(user);
	}

	public void savePolitiquePassword(String regex) {
		configService.setPolitiquePassword(regex);

	}

	public void setTentativeCoMax(int nbtentative) {
		configService.setTentativeCoMax(nbtentative);

	}

	public void reactiveAccount(String login) {
		User user = userDao.findByUserName(login);
		user.setIsLocked(0);
		user.setNbTentativesConn(0);
		userDao.attachDirty(user);
	}

	public ArrayList<User> getUsersDisabled() {
		ArrayList<User> users =  userDao.getUsersDisabled();
		return users;
	}

	public boolean isUserLocked(String username) {
		User user = userDao.findByUserName(username);
		return (user.getIsLocked()==1);
	}

	public boolean userExist(String login, String mail) {
		return (userDao.checkIfUsernameAndMailExist(login,mail));
	}

	public boolean ckeckDefi(String login, ArrayList<Integer> values, ArrayList<Integer> adresses) {
		MatriceUser matrice = matriceDao.getMatrice(login);
		
		
		String [] valuesDb = matrice.getValue().split(",");
		for (int i = 0; i < adresses.size(); i++) {
			Integer valuedb =Integer.parseInt(valuesDb[adresses.get(i)-1]); 
			System.out.println("valeur db : "+ valuedb + " valeur user : "+values.get(i));
			if(valuedb != values.get(i) ){
				return false;
			}				
		}
		matrice.setCurrentCheckSuccess(1);
		matriceDao.persist(matrice);
		return true;
	}

	public boolean isDefiReussi(String userName) {
		return matriceDao.defiReussi(userName);
	}

	public void ResetDefiSuccesCheck(String userName) {
		matriceDao.resetdefiSuccessCheck(userName);
		
	}

}
