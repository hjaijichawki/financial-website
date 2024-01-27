
package com.octest.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.Outils;
import com.octest.beans.Personnel;
import com.octest.beans.Transaction;
import com.octest.beans.User;
import com.octest.beans.Aes2;

import com.octest.beans.Years;
import com.octest.dao.DaoFactory;
import com.octest.dao.OutilsDao;
import com.octest.dao.PersonnelDao;
import com.octest.dao.TransactionDao;
import com.octest.dao.YearDao;
import com.octest.forms.Cconnectionform;
import com.octest.dao.UserDao;


@WebServlet("/FinancEpt")
public class FinancEpt extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private String[] tbls;
	    public static final int TAILLE_TAMPON = 10240;
	    public static final String CHEMIN_FICHIERS = "C:/Users/Rayan/Desktop/Test project/"; // A changer
		int yearId = 0;


	    
    public FinancEpt() {
        super();
    }
  
	private UserDao UserDao;
  	private TransactionDao transactionDao;
    private YearDao yearDao ;
    private PersonnelDao personnelDao ;
    private OutilsDao outilDao ;


    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.UserDao = daoFactory.getUserDao();
        this.transactionDao = daoFactory.getTransactionDao();
        this.yearDao = daoFactory.getYearDao();
        this.personnelDao = daoFactory.getPersonnelDao();
        this.outilDao = daoFactory.getoutilDao();




    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        request.setAttribute("heure", "jour");
        tbls = new String[] {"kkk","lfff,"};
        request.setAttribute("tbls", tbls);
	    

           this.getServletContext().getRequestDispatcher("/WEB-INF/NewFile.jsp").forward(request, response);	

            
        

       

        	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// TODO Auto-generated method stub
		Cconnectionform form=new Cconnectionform();
		form.verifid(request);
		request.setAttribute("form", form);
   //     response.sendRedirect("/WEB-INF/bonjour.jsp");
		String login = request.getParameter("username");
        String pss = request.getParameter("password");
        String pssenc ="";
        
        try {
			 pssenc = Aes2.encrypt(pss);
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        session.setAttribute("pss", pssenc);
        
        
        List<Integer> years =  yearDao.getAllYears();

        
        
          User admin = new User();
         admin.setEmail(login);
          admin.setPassword(pssenc);
          String page = request.getParameter("page");
          int intpage=0;
          String msgconnection ="";
          try {
               intpage = Integer.parseInt(page);
          }
          catch(NumberFormatException e) {
        	  
          }
          if ("SeConnecter".equals(page)) {         if(pssenc != null) {  	if( UserDao.checkUser(admin) ) {
        	  admin = UserDao.getUser(login, pssenc);
              session.setAttribute("useridd", admin.getuId());
              session.setAttribute("userpassword", admin.getPassword());
              session.setAttribute("username",admin.getFullName());
              session.setAttribute("email", admin.getEmail());
              session.setAttribute("uname", admin.getName());

        	  yearId=yearDao.getMaxYear();
        	  Years yearObj = null;
        	  yearObj = yearDao.getYear(yearId);
        	  if(yearObj.getEtat().equals("nonfini")) {
		              session.setAttribute("yearId", yearId);
		              String msgdebut ="vous etes en annes " + yearId +" terminer votre travail ";
		              request.setAttribute("popupMessage", msgdebut);
		        	    request.setAttribute("showPopup", true);
		        	 this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	}
        	  else {
        		  List<Transaction> transactions = transactionDao.getAllTransactions(yearId); // Replace with the desired year
        		  int nad=0;
        		  int nres=0;
        		  int nfo=0;
        		  int nde=0;
        		  double montant1=0;
        		  double montant2=0;
        		  double montant3=0;
        		  double montant4=0;
        		  String ch="Vous avez ";

        		  for (Transaction transaction : transactions) {
        	          String beneficiaire = transaction.getBeneficiaire();
        	          if (beneficiaire.equals("Administration")&& transaction.getEtat().equals("Non payé")) {
        	              nad++;
        	        	  montant1+=transaction.getMontant();

        	          } else if (beneficiaire.equals("Foyer")&& transaction.getEtat().equals("Non payé")) {
        	        	  nfo++;
        	        	  montant2+=transaction.getMontant();
        	          } else if (beneficiaire.equals("Département")||beneficiaire.equals("Departement") && transaction.getEtat().equals("Non payé")) {
        	        	  montant3+=transaction.getMontant();
        	        	  nde++;

        	          } else if (beneficiaire.equals("Restaurant")&& transaction.getEtat().equals("Non payé")) {
        	        	  montant4+=transaction.getMontant();
        	        	  nres++;

        	          }
        	          
        	      }
        		  int ntotale =nad+nfo+nde+nres;
        		  double  montanttotale= montant1+montant4 +montant2 + montant3;
        		  ch=ch+ ntotale+  "Transaction non payée avec un montant totale " + montanttotale+" repartie de la maniére suivante :" + '\n';
        		  String ch1="Vous avez " + nad + " transactiosn no payées, avec un montant total de "+ montant1 + "TND.";
        		  String ch2="Vous avez " + nfo + " transactiosn no payées, avec un montant total de "+ montant2 + "TND.";
        		  String ch3="Vous avez " + nde + " transactiosn no payées, avec un montant total de "+ montant3 + "TND.";
        		  String ch4="Vous avez " + nres + " transactiosn no payées, avec un montant total de "+ montant4 + "TND.";
        		  Map<String, Double> map = new HashMap<>();
        	        map.put("Administration", montant1);
        	        map.put("Foyer",montant2 );
        	        map.put("Departement", montant3);
        	        map.put("Restaurant", montant4);

        	        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        	        String chordre ="";
        	        Collections.sort(list, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        	        String chstrat="Compte tenu du montant non payé, voici des recommendations pour modérer votre stratégie financière de l'année prochaine, en matière d'achat des produits par ordre décroissant:";
        	        for (Map.Entry<String, Double > entry : list) {
        	        	String c=entry.getKey();
        	             chordre = chordre +   c   + "..";
        	        }
        	        
          		  session.setAttribute("ch", ch);
          		  session.setAttribute("ch1", ch1);
          		  session.setAttribute("ch2", ch2);
          		  session.setAttribute("ch3", ch3);
          		  session.setAttribute("ch4", ch4);
          		  session.setAttribute("chstrat", chstrat);
          		  session.setAttribute("chordre", chordre);




  			  
        		  
        		  
        		  
        		  
        		  
        		  session.setAttribute("yearIdold", yearId);
        		  yearId=yearId+1;
        		  session.setAttribute("yearId", yearId);
	              String msgdebut ="vous etes en annes dddddd" + yearId +" terminer votre travail ";
	              request.setAttribute("popupMessage", msgdebut);
	        	  request.setAttribute("showPopup", true);
	        	  this.getServletContext().getRequestDispatcher("/WEB-INF/bilan.jsp").forward(request, response);
        	  }


   		
   	} else {
   		msgconnection="Veuillez remplir votre coordonnée d'une facon correcte ";
   		session.setAttribute("msgconnection", msgconnection);
        this.getServletContext().getRequestDispatcher("/WEB-INF/NewFile.jsp").forward(request, response);
  	    session.removeAttribute("msgconnection");

    }}}
 
          if ("motedepasseoublie".equals(page)) {
              this.getServletContext().getRequestDispatcher("/WEB-INF/motedepasseoublie.jsp").forward(request, response);
          }
          else if ("Réinitialiser".equals(page)) {
        	  String uid = request.getParameter("Unique_id");
              String secretcode = request.getParameter("Secret_code");
              User adminmotdepasseoublie = new User();
              adminmotdepasseoublie.setuId(uid) ;
              adminmotdepasseoublie.setSecretCode(secretcode) ;
              boolean test=UserDao.UserAuth(adminmotdepasseoublie);
              System.out.println(test);
              if(secretcode != null) {  	if( UserDao.UserAuth(adminmotdepasseoublie) ) {
               
       	       this.getServletContext().getRequestDispatcher("/WEB-INF/New.jsp").forward(request, response);
       		
       	}
       	else {      
       		msgconnection="veuillez verifier votre parametres de reintialisation";
       		session.setAttribute("msgconnection", msgconnection);

       		this.getServletContext().getRequestDispatcher("/WEB-INF/motedepasseoublie.jsp").forward(request, response);
      	    session.removeAttribute("msgconnection");

      ;}  
                  
          }
         
          
  		
        
       
       
  

   
  }
          // to do
          else if ("initialiser".equals(page)) {  
        	  
          String uidnew = request.getParameter("identificateur");
          String password = request.getParameter("password");
          String password2 = request.getParameter("password2");
          String msg = " New password ";
          session.setAttribute("msg", msg);
          try {
			pssenc = Aes2.encrypt(password2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          if(!password.equals(password2)) {msg="le mot de passe et sa confirmation ne sont pas identique ";
          session.setAttribute("msg", msg);

          this.getServletContext().getRequestDispatcher("/WEB-INF/New.jsp").forward(request, response);
          
          }
          else {boolean testnew=  UserDao.updateUserPassword(uidnew, pssenc);
          
          
          if( testnew ) {  
        	  session.removeAttribute("msgconnection");
              this.getServletContext().getRequestDispatcher("/WEB-INF/NewFile.jsp").forward(request, response);

          }
          else {msg=" id not found"; session.setAttribute("msg", msg); this.getServletContext().getRequestDispatcher("/WEB-INF/New.jsp").forward(request, response); }
          
          }      
          
          
          
          
          session.setAttribute("msg", msg);

          }
          else if("nouvelletransaction".equals(page)) {
        	  String beneficiaire="";
        	  double montant=0;
            	String idbe = request.getParameter("idbenifice");
            	if(idbe.startsWith("PER")) {
            		Personnel p = personnelDao.getPersonnelById(idbe);
            		 montant = p.getSalaire();
            		 beneficiaire = p.getAppartenance();
            		
            		
            	}
            	else {
            		Outils o = outilDao.getOutilsById(idbe);
            		 montant = o.getPrix();
            		 beneficiaire = o.getAppartenance();
            		
            	}
          	

          	Date date = Date.valueOf(request.getParameter("date"));
          	int yearId = (int) session.getAttribute("yearId");
            String idtrns=request.getParameter("id_transaction");

          	
            String patternString = "TRANS-" + yearId + "-\\d+"; // Le motif pour vérifier la chaîne
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(idtrns);
            idtrns=idbe+"-"+idtrns;

            Transaction t = transactionDao.getTransactionById(idtrns);

            if (!(matcher.matches())) {
	            String resultat_ajout_transaction ="bien effectue";

            	session.setAttribute("resultat_ajout_transaction", resultat_ajout_transaction);
        		
	      	    request.setAttribute("showPopup", true);
	    	    String popmsg= "Echec :verifier la forme demandé de l'ID" ;
	    	    request.setAttribute("popupMessage", popmsg);
	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
            }
            
             
            else  if(!(t==(null))) {
		
		      	    request.setAttribute("showPopup", true);
		    	    String popmsg= "Echec :verifier l'unicité demandé de l'ID" ;
		    	    request.setAttribute("popupMessage", popmsg);
		          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
		

    	    }

            else {
          	Transaction transaction = new Transaction(date, idtrns, beneficiaire, montant, "Non payé", yearId);
          	transactionDao.addTransaction(transaction);
          	String resultat_ajout_transaction ="bien effectue";
          	session.setAttribute("resultat_ajout_transaction", resultat_ajout_transaction);

      	    request.setAttribute("showPopup", true);
    	    String popmsg= "transaction ajouté avec succée" +idtrns  + yearId;
    	    request.setAttribute("popupMessage", popmsg);
    	    session.removeAttribute("date");
    	    session.removeAttribute("montant");
    	    session.removeAttribute("benificaire");




          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	}

          }
          else if("newpersonnel".equals(page)) {
        	String  errorMsg =";";
        	   String id = request.getParameter("id_personnel");
        	    String salaireParam = request.getParameter("salaire");
        	    String appartenance = request.getParameter("place");
        	    
        	    int yearId = (int) session.getAttribute("yearId");
        	    boolean testpers = true ;
                double salaire = Double.parseDouble(salaireParam);

               
        	    // Check that the id is in the correct format
        	    String patternString = "PERSONNEL"+"-\\d+";
        	    Pattern pattern = Pattern.compile(patternString);
        	    Matcher matcher = pattern.matcher(id);
        	    if (!matcher.matches()) {
        	         errorMsg ="Echec : L'ID doit être de la forme 'PERSONNEL-nombre'";
        	        testpers=false;
        	    }
        	  else if (salaire < 0) {
                     errorMsg = "Echec : Le salaire ne doit pas être négatif";
        	        testpers=false;
}
        	    
        	    List<Personnel> personnels = personnelDao.getAllPersonnel();
        	    for (Personnel personnel : personnels) {
        	        if (personnel.getP_ID().equals(id)) {
        	             errorMsg = "Echec : L'ID doit être unique";
            	        testpers=false;
        	        }
        	        }
        	        if(testpers) {
        	        	Personnel  personnel = new Personnel(id, (float) salaire, appartenance, yearId);
        	            personnelDao.addPersonnel(personnel);
        	            request.setAttribute("showPopup", true);
            	    	  String popmsg= "Personnel ajouté avec succé dans votre liste des personnel à payer " ;
            	    	    request.setAttribute("popupMessage", popmsg);
            	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
        	        	
        	        }
        	        else {
        	        	 request.setAttribute("showPopup", true);
             	    	  String popmsg= errorMsg;
             	    	    request.setAttribute("popupMessage", popmsg);
             	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
        	        	
        	        }
        	    
        	    

        }
          
          
          
          
          else if("newProduit".equals(page)) {
          	String  errorMsg =";";
          	   String id = request.getParameter("id_produit");
          	    String salaireParam = request.getParameter("prix");
          	    String appartenance = request.getParameter("placee");
          	    
          	    int yearId = (int) session.getAttribute("yearId");
          	    boolean testpers = true ;
                  double salaire = Double.parseDouble(salaireParam);

                 
          	    // Check that the id is in the correct format
          	    String patternString = "Produit"+"-\\d+";
          	    Pattern pattern = Pattern.compile(patternString);
          	    Matcher matcher = pattern.matcher(id);
          	    if (!matcher.matches()) {
          	         errorMsg = "Echec :L'ID doit être de la forme 'PERSONNEL-nombre'";
          	        testpers=false;
          	    }
          	  else if (salaire < 0) {
                       errorMsg = "Le prix ne doit pas être négatif";
          	        testpers=false;
  }
          	    
          	    List<Outils> outils = outilDao.getAllOutils();
          	    for (Outils obj : outils) {
          	        if (obj.getId().equals(id)) {
          	             errorMsg = "L'ID doit être unique";
              	        testpers=false;
          	        }
          	        }
          	        if(testpers) {
          	        	Outils  Obj = new Outils(id, (float) salaire, appartenance, yearId);
          	        	outilDao.addOutils(Obj);
          	            request.setAttribute("showPopup", true);
              	    	  String popmsg= "Produit du marché ajouté avec succé dans votre liste de produit  " ;
              	    	    request.setAttribute("popupMessage", popmsg);
              	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
          	        	
          	        }
          	        else {
          	        	 request.setAttribute("showPopup", true);
               	    	  String popmsg= errorMsg;
               	    	    request.setAttribute("popupMessage", popmsg);
               	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
          	        	
          	        }
          	    
          	    

          }
          else if("supprimeproduit".equals(page)) {
        	  boolean supr = true;
    	      String id = request.getParameter("produitid");

        	  supr=outilDao.deleteOutils( id);
        	  if(supr) {
   	    	   request.setAttribute("showPopup", true);
    	    	  String popmsg= "Produit supprimée avec succe " ;
    	    	    request.setAttribute("popupMessage", popmsg);
    	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	

   	    	   
   	       }
   	       else {
   	    	   
   	    	        request.setAttribute("showPopup", true);
     	    	    String popmsg= "Echec : Produit n'existe pas   " ;
     	    	    request.setAttribute("popupMessage", popmsg);
     	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
   	    	   
   	       }
        	  
        	  
          }
          
          else if("supprimepersonnelle".equals(page)) {
        	  boolean supr = true;
    	      String id = request.getParameter("personnelId");

        	  supr=personnelDao.deletePersonnel( id);
        	  if(supr) {
   	    	   request.setAttribute("showPopup", true);
    	    	  String popmsg= "Personnel supprimée avec succe " ;
    	    	    request.setAttribute("popupMessage", popmsg);
    	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	

   	    	   
   	       }
   	       else {
   	    	   
   	    	   request.setAttribute("showPopup", true);
     	    	  String popmsg= "Echec : Personnel n'existe pas   " ;
     	    	    request.setAttribute("popupMessage", popmsg);
     	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
   	    	   
   	       }
        	  
        	  
          }
          
          
          else if(years.contains(intpage) ){
        	  int pagenum =Integer.parseInt(page);
              session.setAttribute("yearId", pagenum);
              this.getServletContext().getRequestDispatcher("/WEB-INF/oldyear.jsp").forward(request, response);	
          }
          else if("supprimetransaction".equals(page)) {
    	      String transactionId = request.getParameter("transactionId");
    	      Transaction t=  transactionDao.getTransactionById(transactionId);
    	      boolean delete = false ;
    	      if(t!=null)
    	      {   delete=    transactionDao.deleteTransaction( t);}
    	       if(delete) {
    	    	   request.setAttribute("showPopup", true);
     	    	  String popmsg= "transaction supprimée avec succe " +transactionId;
     	    	    request.setAttribute("popupMessage", popmsg);
     	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	

    	    	   
    	       }
    	       else {
    	    	   
    	    	   request.setAttribute("showPopup", true);
      	    	   String popmsg= "transaction deja  supprimée avec succe " ;
      	    	    request.setAttribute("popupMessage", popmsg);
      	          	this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	
    	    	   
    	       }
    	      

        	  
        	  
          }
          else if("updateTransactionStateToPaid".equals(page) ) {
        	      boolean updated = false;
        	      boolean hasEarlierTransaction = false; // initialize the boolean variable to false
        	      String datemsg="";
        	      String transactionId = request.getParameter("transactionId");
        	      Transaction t=  transactionDao.getTransactionById(transactionId);
        	      if(!(t.getEtat().equals("Payé"))) {
        	      double tolere =0;
        	      double depense =0;
        	      double montantnew=t.getMontant();
        	      List<Transaction> administrationTransactions = new ArrayList<>();
        	      List<Transaction> foyerTransactions = new ArrayList<>();
        	      List<Transaction> departementTransactions = new ArrayList<>();
        	      List<Transaction> restaurantTransactions = new ArrayList<>();
        	      List<Transaction> transactions = transactionDao.getAllTransactions(yearId); // Replace with the desired year
        	     

        	      for (Transaction transaction : transactions) {
        	          String beneficiaire = transaction.getBeneficiaire();
        	          if (beneficiaire.equals("Administration")&& transaction.getEtat().equals("Non payé")) {
        	              administrationTransactions.add(transaction);
        	          } else if (beneficiaire.equals("Foyer")&& transaction.getEtat().equals("Non payé")) {
        	              foyerTransactions.add(transaction);
        	          } else if (beneficiaire.equals("Département")||beneficiaire.equals("Departement") && transaction.getEtat().equals("Non payé")) {
        	              departementTransactions.add(transaction);
        	          } else if (beneficiaire.equals("Restaurant")&& transaction.getEtat().equals("Non payé")) {
        	              restaurantTransactions.add(transaction);
        	          }
        	      }


        	      Years yearObj = null;

        	      yearObj = yearDao.getYear(t.getYear());

        	      if(t.getBeneficiaire().equals("Département")||t.getBeneficiaire().equals("Departement")) {
		        	    	  tolere = yearObj.getDepartement_t();
		        	    	  depense =yearObj.getDepartement_d();
		        	    	  for (Transaction transaction : departementTransactions) {
		        	    		    if (transaction.getDate().compareTo(t.getDate()) < 0 ) {
		        	    		        hasEarlierTransaction = true;
		        	    		        datemsg=  transaction.getDate().compareTo(t.getDate()) +"Vous ne pouvez pas payer cette transaction, car il y en a des transactions antérieures à payer\r\n"
		        	    		        		;// set the boolean variable to true if an earlier transaction is found
		        	    		        break;
		        	    		    }
		        	    		}
		        	    	  if(depense+montantnew<=tolere && (!hasEarlierTransaction)  ) {        	     
		        	    		  updated = transactionDao.updateTransactionStateToPaid(transactionId);
		        	    		  float x= (float) ( depense+montantnew);
		        	    		  float  y= (float) ( yearObj.getTotalDepense() +montantnew);
		        	    		  yearObj.setTotalDepense(y);
		        	    		  yearObj.setDepartement_d(x);
		        	    		 
		        	    		  yearDao.updateYear(yearObj);
		        	    		  
		        	    	  		}
		        	    	  else  if(depense+montantnew>tolere) { datemsg="Echec :Le budget disponible n'est pas suffisant.\r\n"
		        	    	  		; }
        	      }
        	      else  if(t.getBeneficiaire().equals("Restaurant")) {
        	    	  tolere = yearObj.getResT();
        	    	  depense =yearObj.getdRestaurant();
        	    	  for (Transaction transaction : restaurantTransactions) {
      	    		    if (transaction.getDate().compareTo(t.getDate()) < 0 ) {
      	    		        hasEarlierTransaction = true;
      	    		        datemsg=   "Echec : Vous ne pouvez pas payer cette transaction, car il y en a des transactions antérieures à payer\r\n"
      	    		        		;// set the boolean variable to true if an earlier transaction is found
      	    		        break;
      	    		    }
      	    		}
        	    	  if((depense+montantnew<=tolere) && (!hasEarlierTransaction)) {        	     
        	    		  updated = transactionDao.updateTransactionStateToPaid(transactionId);
        	    		  float x= (float) ( depense+montantnew);
        	    		  yearObj.setdRestaurant(x);
        	    		  float  y= (float) ( yearObj.getTotalDepense() +montantnew);
        	    		  yearObj.setTotalDepense(y);
        	    		  yearDao.updateYear(yearObj);
        	    		  

        	    		  
        	    	  		}
        	    	  else  if(depense+montantnew>tolere) { datemsg="Le budget disponible n'est pas suffisant.\r\n"
	        	    	  		; }
        	      }
        	      else  if(t.getBeneficiaire().equals("Foyer")) {
        	    	  tolere = yearObj.getFoT();
        	    	  depense =yearObj.getdFoyer();
        	    	  for (Transaction transaction :  foyerTransactions) {
      	    		    if (transaction.getDate().compareTo(t.getDate()) < 0 ) {
      	    		        hasEarlierTransaction = true;
      	    		        datemsg=  transaction.getDate().compareTo(t.getDate()) +"Vous ne pouvez pas payer cette transaction, car il y en a des transactions antérieures à payer\r\n"
      	    		        		;// set the boolean variable to true if an earlier transaction is found
      	    		        break;
      	    		    }
      	    		}
        	    	  if((depense+montantnew<=tolere) && (!hasEarlierTransaction)) {        	     
        	    		  updated = transactionDao.updateTransactionStateToPaid(transactionId);
        	    		  float x= (float) ( depense+montantnew);
        	    		  yearObj.setdFoyer(x);
        	    		  float  y= (float) ( yearObj.getTotalDepense() +montantnew);
        	    		  yearObj.setTotalDepense(y);
        	    		  yearDao.updateYear(yearObj);

        	    		  
        	    	  		}
        	    	  else  if(depense+montantnew>tolere) { datemsg="Echec :Le budget disponible n'est pas suffisant.\r\n"
	        	    	  		; }
        	      }
        	      else  if(t.getBeneficiaire().equals("Administration")) {
        	    	  tolere = yearObj.getAdT();
        	    	  depense =yearObj.getdAdministration();
        	    	  for (Transaction transaction :  administrationTransactions) {
        	    		    if (transaction.getDate().compareTo(t.getDate()) < 0 ) {
        	    		        hasEarlierTransaction = true;
        	    		        datemsg=  "Echec :Vous ne pouvez pas payer cette transaction, car il y en a des transactions antérieures à payer\r\n"
        	    		        		;// set the boolean variable to true if an earlier transaction is found
        	    		        break;
        	    		    }
        	    		}
        	    	  if((depense+montantnew<=tolere) && (!hasEarlierTransaction)) {        	     
        	    		  updated = transactionDao.updateTransactionStateToPaid(transactionId);
        	    		  float x= (float) ( depense+montantnew);
        	    		  yearObj.setdAdministration(x);
        	    		  float  y= (float) ( yearObj.getTotalDepense() +montantnew);
        	    		  yearObj.setTotalDepense(y);
        	    		  yearDao.updateYear(yearObj);

        	    		  
        	    	  		}
        	    	  else  if(depense+montantnew>tolere) { datemsg="Le budget disponible n'est pas suffisant.\r\n"
	        	    	  		; }    
        	      }}
        	    	  
        	    
				if (updated) { 
        	    	  request.setAttribute("showPopup", true);
        	    	  String popmsg= "transaction( "+transactionId+" ) "+" paye avec succe " +transactionId;
        	    	    request.setAttribute("popupMessage", popmsg);
        	          // show success message
        	      } else  {
        	    	  request.setAttribute("showPopup", true);
					String popmsg= "( " +transactionId + ") " + datemsg ;
        	    	    request.setAttribute("popupMessage", popmsg);
        	          // show error message
        	      }
        	  
              this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	


          }
          else if ("nouveau user".equals(page)) {
        	  
        	  
              this.getServletContext().getRequestDispatcher("/WEB-INF/newUser.jsp").forward(request, response);	

        	  
          }
          else if ("mainpage".equals(page)) {
        	  yearId=yearDao.getMaxYear();
        	  Years yearObj = null;
        	  yearObj = yearDao.getYear(yearId);
        	  if(yearObj.getEtat().equals("nonfini")) {
		              session.setAttribute("yearId", yearId);
		              String msgdebut ="vous etes en annes " + yearId +"terminer votre travail ";
		              request.setAttribute("popupMessage", msgdebut);
		        	    request.setAttribute("showPopup", true);
		        	 this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	}
        	  else {
        		  List<Transaction> transactions = transactionDao.getAllTransactions(yearId); // Replace with the desired year
        		  int nad=0;
        		  int nres=0;
        		  int nfo=0;
        		  int nde=0;
        		  double montant1=0;
        		  double montant2=0;
        		  double montant3=0;
        		  double montant4=0;
        		  String ch="Vous avez ";

        		  for (Transaction transaction : transactions) {
        	          String beneficiaire = transaction.getBeneficiaire();
        	          if (beneficiaire.equals("Administration")&& transaction.getEtat().equals("Non payé")) {
        	              nad++;
        	        	  montant1+=transaction.getMontant();

        	          } else if (beneficiaire.equals("Foyer")&& transaction.getEtat().equals("Non payé")) {
        	        	  nfo++;
        	        	  montant2+=transaction.getMontant();
        	          } else if (beneficiaire.equals("Département")||beneficiaire.equals("Departement") && transaction.getEtat().equals("Non payé")) {
        	        	  montant3+=transaction.getMontant();
        	        	  nde++;

        	          } else if (beneficiaire.equals("Restaurant")&& transaction.getEtat().equals("Non payé")) {
        	        	  montant4+=transaction.getMontant();
        	        	  nres++;

        	          }
        	          
        	      }
        		  int ntotale =nad+nfo+nde+nres;
        		  double  montanttotale= montant1+montant4 +montant2 + montant3;
        		  ch=ch+ ntotale+  "Transaction non payée avec un montant totale " + montanttotale+" repartie de la maniére suivante :" + '\n';
        		  String ch1="Vous avez " + nad + " transactiosn no payées, avec un montant total de "+ montant1 + "TND.";
        		  String ch2="Vous avez " + nfo + " transactiosn no payées, avec un montant total de "+ montant2 + "TND.";
        		  String ch3="Vous avez " + nde + " transactiosn no payées, avec un montant total de "+ montant3 + "TND.";
        		  String ch4="Vous avez " + nres + " transactiosn no payées, avec un montant total de "+ montant4 + "TND.";
        		  Map<String, Double> map = new HashMap<>();
        	        map.put("Administration", montant1);
        	        map.put("Foyer",montant2 );
        	        map.put("Departement", montant3);
        	        map.put("Restaurant", montant4);

        	        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        	        String chordre ="";
        	        Collections.sort(list, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        	        String chstrat="Compte tenu du montant non payé, voici des recommendations pour modérer votre stratégie financière de l'année prochaine, en matière d'achat des produits par ordre décroissant:";
        	        for (Map.Entry<String, Double > entry : list) {
        	        	String c=entry.getKey();
        	             chordre =  chordre+ c + "..";
        	        }
        	        
          		  session.setAttribute("ch", ch);
          		  session.setAttribute("ch1", ch1);
          		  session.setAttribute("ch2", ch2);
          		  session.setAttribute("ch3", ch3);
          		  session.setAttribute("ch4", ch4);
          		  session.setAttribute("chstrat", chstrat);
          		  session.setAttribute("chordre", chordre);




  			  
        		  
        		  
        		  
        		  
        		  
        		  session.setAttribute("yearIdold", yearId);
        		  yearId=yearId+1;
        		  session.setAttribute("yearId", yearId);
	              String msgdebut ="vous etes en annes dddddd" + yearId +"terminer votre travail ";
	              request.setAttribute("popupMessage", msgdebut);
	        	  request.setAttribute("showPopup", true);
	        	  this.getServletContext().getRequestDispatcher("/WEB-INF/bilan.jsp").forward(request, response);
        	  }
        	  

        	  
          }
          else if ("logout".equals(page)) {
        	   
              this.getServletContext().getRequestDispatcher("/WEB-INF/NewFile.jsp").forward(request, response);	

          }
		  else if ("newuserfinish".equals(page)) {
			        	  String userIdStr = request.getParameter("iduser");
			              String secretCode = request.getParameter("secretcode");
			              String fullName = request.getParameter("usernom") ;
			             String name=	  request.getParameter("userprenom");
			              String email = request.getParameter("emailinput");
			              String password = request.getParameter("psww");
			              String passwordnewenc ="Echec :";
			              try {
							 passwordnewenc = Aes2.encrypt(password);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			              String passwordconf = request.getParameter("pswwconf");
			              String secretCodeadmin = request.getParameter("secretcodeadmin");
			              String msgnewuser = ""; 
			              boolean testnewuser = true ;
			              List<User> users = UserDao.getUsers();
			              for (User user : users) {
			                  if (user.getuId() == userIdStr) {
			                	  msgnewuser= msgnewuser+ "  ,  "+ "User ID already exists";
			                	  testnewuser = false ;
			                  }
			              }
			
			              // Check if full name only contains letters
			              if (!fullName.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")) {
			            	  msgnewuser=msgnewuser+ "  ,  "+ "Full name should only contain letters";
			            	  testnewuser = false ;
			
			              }
			              if (!name.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")) {
			            	  msgnewuser=msgnewuser+ "  ,  "+ " name should only contain letters";
			            	  testnewuser = false ;
			
			              }
			
			              // Check if password contains at least 8 characters, numbers, lowercase and uppercase letters and special characters
			              if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$")) {
			            	  msgnewuser=msgnewuser+ "  ,  "+ "Password should contain at least 8 characters, numbers, lowercase and uppercase letters and special characters";
			            	  testnewuser = false ;
			
			              }
			
			              // Check if password matches confirmation password
			              if (!password.equals(passwordconf)) {
			            	  msgnewuser=msgnewuser+ " , "+ "Passwords don't match";
			            	  testnewuser = false ;
			
			              }
			
			              // Check if admin secret code exists in database
			              boolean adminCodeExists = false;
			              for (User user : users) {
			                  if (user.getSecretCode().equals(secretCodeadmin)) {
			                      adminCodeExists = true;
			                      break;
			                  }
			              }
			              if (!adminCodeExists) {
			            	  msgnewuser=msgnewuser+ " , "+ "Invalid admin secret code";
			            	  testnewuser = false ;
			
			              }
			
			          
			
			
			          if(testnewuser) {   
			              User user = new User();
			              user.setuId(userIdStr);
			              user.setName(name);
			              user.setSecretCode(secretCode);
			              user.setFullName(fullName);
			              user.setEmail(email);
			              user.setPassword(passwordnewenc);
			
			              UserDao.addUser(user);
			              request.setAttribute("showPopup", true);
			              msgnewuser=" user added successfully";
				    	    request.setAttribute("popupMessage", msgnewuser);
			        	  
			       	   
			              this.getServletContext().getRequestDispatcher("/WEB-INF/newUser.jsp").forward(request, response);	}
			          else  {
			        	  msgnewuser=" user not added "+msgnewuser;
			        	  request.setAttribute("showPopup", true);
				    	  request.setAttribute("popupMessage", msgnewuser);
			      	  
			     	   
			            this.getServletContext().getRequestDispatcher("/WEB-INF/newUser.jsp").forward(request, response);
			        	  
			        	  
			        	  
			          }
			
			          }
			
		  else if ("finish".equals(page)) {
			  List<Transaction> transactions = transactionDao.getAllTransactions(yearId); // Replace with the desired year
    		  int nad=0;
    		  int nres=0;
    		  int nfo=0;
    		  int nde=0;
    		  double montant1=0;
    		  double montant2=0;
    		  double montant3=0;
    		  double montant4=0;
    		  String ch="Vous avez ";

    		  for (Transaction transaction : transactions) {
    	          String beneficiaire = transaction.getBeneficiaire();
    	          if (beneficiaire.equals("Administration")&& transaction.getEtat().equals("Non payé")) {
    	              nad++;
    	        	  montant1+=transaction.getMontant();

    	          } else if (beneficiaire.equals("Foyer")&& transaction.getEtat().equals("Non payé")) {
    	        	  nfo++;
    	        	  montant2+=transaction.getMontant();
    	          } else if (beneficiaire.equals("Département")||beneficiaire.equals("Departement") && transaction.getEtat().equals("Non payé")) {
    	        	  montant3+=transaction.getMontant();
    	        	  nde++;

    	          } else if (beneficiaire.equals("Restaurant")&& transaction.getEtat().equals("Non payé")) {
    	        	  montant4+=transaction.getMontant();
    	        	  nres++;

    	          }
    	          
    	      }
    		  int ntotale =nad+nfo+nde+nres;
    		  double  montanttotale= montant1+montant4 +montant2 + montant3;
    		  ch=ch+ ntotale+  "Transaction non payée avec un montant totale " + montanttotale+" repartie de la maniére suivante :" + '\n';
    		  String ch1="Vous avez " + nad + "  transactiosn no payées, avec un montant total de "+ montant1 + "TND.";
    		  String ch2="Vous avez " + nfo + " transactiosn no payées, avec un montant total de "+ montant2 + "TND.";
    		  String ch3="Vous avez " + nde + " transactiosn no payées, avec un montant total de "+ montant3 + "TND.";
    		  String ch4="Vous avez " + nres + " transactiosn no payées, avec un montant total de "+ montant4 + "TND.";
    		  Map<String, Double> map = new HashMap<>();
    	        map.put("Administration", montant1);
    	        map.put("Foyer",montant2 );
    	        map.put("Departement", montant3);
    	        map.put("Restaurant", montant4);

    	        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
    	        String chordre ="";
    	        Collections.sort(list, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
    	        String chstrat="Compte tenu du montant non payé, voici des recommendations pour modérer votre stratégie financière de l'année prochaine, en matière d'achat des produits par ordre décroissant:";
    	        for (Map.Entry<String, Double > entry : list) {
    	        	String c=entry.getKey();
    	             chordre = chordre +   c + "..";
    	        }
    	        
      		  session.setAttribute("ch", ch);
      		  session.setAttribute("ch1", ch1);
      		  session.setAttribute("ch2", ch2);
      		  session.setAttribute("ch3", ch3);
      		  session.setAttribute("ch4", ch4);
      		  session.setAttribute("chstrat", chstrat);
      		  session.setAttribute("chordre", chordre);




			  
    		  
    		  
    		  
    		  
    		  
    		  session.setAttribute("yearIdold", yearId);
    		  yearDao.updateYearState(yearId, "fini");
    		  yearId=yearId+1;
    		  session.setAttribute("yearId", yearId);
              String msgdebut ="vous etes en annes " + yearId +"terminer votre travail ";
              request.setAttribute("popupMessage", msgdebut);
        	  request.setAttribute("showPopup", true);
        	  this.getServletContext().getRequestDispatcher("/WEB-INF/bilan.jsp").forward(request, response);
    	  }
			
    	  

		  else if ("startnewyear".equals(page)) {
			  
			  
	            this.getServletContext().getRequestDispatcher("/WEB-INF/newyear.jsp").forward(request, response);
		  }
		  else if ("submitnewyear".equals(page)) {
			  float departements = Float.parseFloat(request.getParameter("Departements"));
			  float foyer = Float.parseFloat(request.getParameter("Foyer"));
			  float restaurant = Float.parseFloat(request.getParameter("Restaurant"));
			  float administration = Float.parseFloat(request.getParameter("administration"));
			  Years yearObj = new Years();

			    yearObj.setAdT(administration);
			    yearObj.setdAdministration(0);
			    yearObj.setDepartement_d(0);
			    yearObj.setDepartement_t(departements);
			    yearObj.setdFoyer(0);
			    yearObj.setFoT(foyer);
			    yearObj.setResT(restaurant);
			    yearObj.setdRestaurant(0);
			    yearObj.setTotalTolere(administration+restaurant+foyer+departements);
			    yearObj.setTotalDepense(0);
			    yearObj.setEtat("nonfini");
			    yearObj.setYear(yearId);
	            yearDao.addYear(yearObj);

			    
	              this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);	

			    

			  
			  
		  }
		  else if ("monprofil".equals(page)) {
			  
              this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);	

		  }
          else if ("newpsww".equals(page)) { 
        	   String uidnew = (String) session.getAttribute("useridd");
               String passwordold = request.getParameter("old");
               String pp= (String) session.getAttribute("userpassword");
               String password = request.getParameter("newpsww");
               String password2 = request.getParameter("pswwconf");
               String msg = " New password ";
                pssenc ="";
                String pssencold="";
               try {
       			 pssenc = Aes2.encrypt(password);
       			pssencold =  Aes2.encrypt(passwordold);
       		} catch (Exception e3) {
       			// TODO Auto-generated catch block
       			e3.printStackTrace();
       		}
               session.setAttribute("msg", msg);
               if(!pssencold.equals(pp)) { 
            	   request.setAttribute("showPopup", true);
	                msg=" ancient mot de passe faux";
		    	    request.setAttribute("popupMessage", msg);   
		               this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
}
               else    if(!password.equals(password2)) {msg="le MDP et sa confirmation ne sont pas identiques";
               request.setAttribute("popupMessage", msg);  
               this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
               
               }
               else { UserDao.updateUserPassword(uidnew, pssenc);
               session.setAttribute("userpassword",password);
               msg="password updated succesfully";
               request.setAttribute("popupMessage", msg);  
               session.setAttribute("useridd",pp);
               this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
               
        	  
        	  
          }
             
               
          }
          else if ("newname".equals(page)) {
	             String name=	  request.getParameter("newname");
	              
	           String s2 =  (String) session.getAttribute("uname");
	           String s3 = (String) session.getAttribute("email");
	             boolean testnew = true ;
	             String msgnewuser="modification succesfully "+name;
	            
	              if (!name.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")) {
	            	  msgnewuser=msgnewuser+ "et "+ " name should only contain letters";
	            	  testnew = false ;
	
	              }
	              if (testnew) {
	           	   String uidnew = (String) session.getAttribute("useridd");

	            	  
	            	  UserDao.updateUserDetails(uidnew, s2, name, s3);
	                  request.setAttribute("popupMessage", msgnewuser);  
	                  session.setAttribute("username",name);
	                  this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	            	  
	              }
	              else {
		  	           	request.setAttribute("popupMessage", "echec");  
		  	          this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
		              }
		              
	              
        	  
        	  
        	  
          }
          else if ("newfullname".equals(page)) {
        	  String fullName = request.getParameter("newfullname") ;
	        
	           String s3 = (String) session.getAttribute("email");
	           String s4 =  (String) session.getAttribute("username");
	             boolean testnew = true ;
	             String msgnewuser="modification succesfully ";
	             if (!fullName.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")) {
	            	  msgnewuser=msgnewuser+ " , "+ "Full name should only contain letters";
	            	  testnew = false ;
	
	              }
	              
	              if (testnew) {
	           	   String uidnew = (String) session.getAttribute("useridd");

	           	request.setAttribute("popupMessage", msgnewuser);  
                session.setAttribute("uname",fullName);
                this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
          	  
	            	  UserDao.updateUserDetails(uidnew, fullName, s4, s3);
	            	  
	              }
	              else {
	  	           	request.setAttribute("popupMessage", msgnewuser);  
	  	          this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	              }
	              
        	  
        	  
        	  
          }

          else if ("newemail".equals(page)) {
        	  String fullName = request.getParameter("newemail") ;
	              
	      
	           String s2 =  (String) session.getAttribute("username");
	         
	           String s4 =  (String) session.getAttribute("uname");
	             boolean testnew = true ;
	             String msgnewuser="Modification avec succes ";
	            
	              
	              if (testnew) {
	           	   String uidnew = (String) session.getAttribute("useridd");

	            	  
	            	  UserDao.updateUserDetails(uidnew, s4, s2, fullName);
	            	 	request.setAttribute("popupMessage", msgnewuser);  
	                    session.setAttribute("email",fullName);
	                    this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	              	  
	            	  
	              }
	              else {
		  	           	request.setAttribute("popupMessage", msgnewuser);  
		  	          this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
		              }
		              
	              
        	  
        	  
        	  
          }




          

          
        
  
    	 
    
	   /*    String nom = request.getParameter("nom");
	        
	        request.setAttribute("nom", nom);
	        
	        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);*/
  
	// TODO Auto-generated method stub

	
}
  

}




