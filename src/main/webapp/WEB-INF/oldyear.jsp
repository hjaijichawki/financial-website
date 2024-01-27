<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.octest.beans.Years" %>
<%@ page import="com.octest.dao.DaoFactory" %>
<%@ page import="com.octest.dao.YearDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.octest.beans.Transaction" %> <!-- Replace with the actual package name of your Transaction class -->
<%@ page import="com.octest.dao.TransactionDao" %> <!-- Replace with the actual package name of your TransactionDao class -->
<%@ page import="com.octest.dao.DaoFactory" %>
<%
// Retrieve data from the database
DaoFactory daoFactory = DaoFactory.getInstance();
YearDao yearDao = daoFactory.getYearDao();
Years yearObj = null;
int yearId = (int) session.getAttribute("yearId");
yearObj = yearDao.getYear(yearId);
String resultat_ajout_transaction = (String) session.getAttribute("resultat_ajout_transaction");


TransactionDao transactionDao = daoFactory.getTransactionDao();

// Get transactions for the year 2020
List<Transaction> transactions = transactionDao.getAllTransactions(yearId); // Replace with the desired year
List<Integer> years =  yearDao.getAllYears();

// Create a list to store the transaction data
List<Float> data = new ArrayList<Float>();
List<Transaction> administrationTransactions = new ArrayList<>();
List<Transaction> foyerTransactions = new ArrayList<>();
List<Transaction> departementTransactions = new ArrayList<>();
List<Transaction> restaurantTransactions = new ArrayList<>();

for (Transaction transaction : transactions) {
    String beneficiaire = transaction.getBeneficiaire();
    if (beneficiaire.equals("Administration")) {
        administrationTransactions.add(transaction);
    } else if (beneficiaire.equals("Foyer")) {
        foyerTransactions.add(transaction);
    } else if (beneficiaire.equals("Département")) {
        departementTransactions.add(transaction);
    } else if (beneficiaire.equals("Restaurant")) {
        restaurantTransactions.add(transaction);
    }
}





data.add(yearObj.getdFoyer());
data.add(yearObj.getFoT());
data.add(yearObj.getdRestaurant());
data.add(yearObj.getResT());
data.add(yearObj.getDepartement_d());
data.add(yearObj.getDepartement_t());
data.add(yearObj.getdAdministration());
data.add(yearObj.getAdT());
data.add(yearObj.getTotalDepense());
data.add(yearObj.getTotalTolere());

float x=0;x=yearObj.getTotalTolere();



%>

<!DOCTYPE html>
<html lang="en">

<head >



    <meta charset="utf-8">
    <title>Financ'EPT Dashboard</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="images/icon.png" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


       <!-- Sidebar Start -->
       <div class="sidebar pe-4 pb-3">
        <nav class="navbar bg-light navbar-light">
            <!-- <a href="index.html" class="navbar-brand mx-4 mb-3"> -->
                <h3 class="text-primary"> Financ'EPT</h3>
            </a>
            <div class="d-flex align-items-center ms-4 mb-4">
                <div class="position-relative">
                    <img class="rounded-circle" src="images/Sans titre(1).png" alt="" style="width: 40px; height: 40px;">
                    <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                </div>
                <div class="ms-3">
                   <h6 class="mb-0"><%=  session.getAttribute("username")  %></h6>
                    <span><%=  session.getAttribute("uname")  %></span>
                </div>
            </div>
            <div class="navbar-nav w-100">
            
                       <form   method="post" action="Home" >
            
            
             <button type="submit"  name="page" value="mainpage" style="border:none;" style="background-color: transparent;" class="nav-item nav-link" ><i class="fa fa-keyboard me-2"></i>Page d'accueil</button>
              <input type="hidden" name="action" value="submit">
                
                
                
                
                <div class="nav-item dropdown">
                    <a href="année dernière.html" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>Années dernières</a>
								                    <div class="dropdown-menu bg-transparent border-0">
								                       <% 
								        for(Integer year : years) {
								    %>
								        <button type="submit" name="page" value=<%= year %> class="dropdown-item"><%= year %></button>
                        				<input type="hidden" name="action" value="submit">
								    <% } %>

                    </div>
                     <button type="submit"  name="page" value="nouveau user" style="border:none;" style="background-color: transparent;" class="nav-item nav-link" ><i class="fa fa-keyboard me-2"></i>Nouveau</button>
                     <input type="hidden" name="action" value="submit">
                
                
                    
                    
                    
                     
                        
                    
                    
                    <!--<div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown"><i class="far fa-file-alt me-2"></i>Pages</a>
                        <div class="dropdown-menu bg-transparent border-0">
                            <a href="signin.html" class="dropdown-item">Sign In</a>
                            <a href="signup.html" class="dropdown-item">Sign Up</a>
                            <a href="404.html" class="dropdown-item">404 Error</a>
                            <a href="blank.html" class="dropdown-item active">Blank Page</a>
                        </div>
                    </div> -->
                    
                    
                    
                </div>
                </form>
            </nav>
        </div>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
            <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                    <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                </a>
                <a href="#" class="sidebar-toggler flex-shrink-0">
                    <i class="fa fa-bars"></i>
                </a>
               
                <div class="navbar-nav align-items-center ms-auto">
                    <div class="nav-item dropdown">
                        
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                            <a href="#" class="dropdown-item">
                                <div class="d-flex align-items-center">
                                    <img class="rounded-circle" src="Sans titre(1).png" alt="" style="width: 40px; height: 40px;">
                                    <div class="ms-2">
                                        <h6 class="fw-normal mb-0">User1 vous a envoyé un message.</h6>
                                        <small>Il y a 15 minutes.</small>
                                    </div>
                                </div>
                            </a>
                            <hr class="dropdown-divider">
                            <a href="#" class="dropdown-item">
                                <div class="d-flex align-items-center">
                                    <img class="rounded-circle" src="Sans titre(1).png" alt="" style="width: 40px; height: 40px;">
                                    <div class="ms-2">
                                        <h6 class="fw-normal mb-0">User2 vous a envoyé un message.</h6>
                                        <small>Il ya 20 minutes.</small>
                                    </div>
                                </div>
                            </a>
                            <hr class="dropdown-divider">
                            <a href="#" class="dropdown-item">
                                <div class="d-flex align-items-center">
                                    <img class="rounded-circle" src="Sans titre(1).png" alt="" style="width: 40px; height: 40px;">
                                    <div class="ms-2">
                                        <h6 class="fw-normal mb-0">User5 vous a envoyé un message.</h6>
                                        <small>Il y a 5 minutes.</small>
                                    </div>
                                </div>
                            </a>
                            <hr class="dropdown-divider">
                            <a href="#" class="dropdown-item text-center">Voir tous les messages</a>
                        </div>
                    </div>
                    <div class="nav-item dropdown">
                       
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                            <a href="#" class="dropdown-item">
                                <h6 class="fw-normal mb-0">Profile mis à jour avec succès.</h6>
                                <small>il y a 1 jour</small>
                            </a>
                            <hr class="dropdown-divider">
                            <a href="#" class="dropdown-item">
                                <h6 class="fw-normal mb-0">Nouvel utilisateur ajouté au réseau.</h6>
                                <small>Il y a 20 secondes</small>
                            </a>
                            <hr class="dropdown-divider">
                            <a href="#" class="dropdown-item">
                                <h6 class="fw-normal mb-0">Mot de passe changé avec succès.</h6>
                                <small>Il y a 2 heures.</small>
                            </a>
                            <hr class="dropdown-divider">
                            <a href="#" class="dropdown-item text-center">Voir toutes les notifications</a>
                        </div>
                    </div>
				<form   method="post" action="Home" >
                    
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <img class="rounded-circle me-lg-2" src="images/Sans titre(1).png" alt="" style="width: 40px; height: 40px;">
                            <span class="d-none d-lg-inline-flex"></span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                              <button type="submit"  name="page" value="monprofil" style="border:none;" style="background-color: transparent;"  >Mon Profil</button>
            				  <input type="hidden" name="action" value="submit">
                        	  <button type="submit"  name="page" value="logout" style="border:none;" style="background-color: transparent;"  >Se Déconnecter</button>
            				  <input type="hidden" name="action" value="submit">
                        
                        </div>
                    </div>
                 </form>
                </div>
            </nav>
            <!-- Navbar End -->


            


            <!-- Sales Chart Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    
                    <div class="col-sm-12 col-xl-6">
                        <div class="bg-light text-center rounded p-4">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <h6 class="mb-0"> Dépenses</h6>
                            </div>
                            <canvas id="salse-revenue"></canvas>
                        </div>
                    </div>
                    <div class="col-sm-12 col-xl-6">
                        <div class="bg-light rounded h-100 p-4">
                            <h6 class="mb-4">Tableau des dépenses</h6>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        
                                        <th scope="col">Partition de l'école cible de dépense</th>
                                        <th scope="col">Dépenses en TND</th>
                                        <th scope="col"style="justify-content: center;">maximum de dépenses</th>
                                    </tr>
                                </thead>
                              <tbody>
    <tr>
        <th scope="row">1</th>
        
        <td>Foyer</td>
        <td><%= data.get(0) %></td>
        <td><%= data.get(1) %></td>
    </tr>
    <tr>
        <th scope="row">2</th>
        
        <td>Restaurant</td>
        <td><%= data.get(2) %></td>
        <td><%= data.get(3) %></td>
    </tr>
    <tr>
        <th scope="row">3</th>
        
        <td>Département</td>
        <td><%= data.get(4) %></td>
        <td><%= data.get(5) %></td>
    </tr>
 
    <tr>
        <th scope="row">4</th>
        
        <td>Administration</td>
        <td><%= data.get(6) %></td>
        <td><%= data.get(7) %></td>
    </tr>
    <tr>
        <th scope="row">7</th>
        
        <td><h6>Total</h6></td>
        <td><%= data.get(8) %></td>
        <td><%= data.get(9) %></td>
    </tr>
</tbody>
                            </table>
                        </div>
                    </div>
                    
                </div>
            </div>
            
            <!-- Sales Chart End -->

            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">administrationTransactions</h6>
                    </div>
                    <div class="table-responsive">
                        <table class="table text-start align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col"><input class="form-check-input" type="checkbox"></th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Transaction</th>
                                    <th scope="col">Bénéficiaire</th>
                                    <th scope="col">Montant</th>
                                    <th scope="col">État</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                             <tbody>
        <%-- Iterate over the transactions list and generate table rows dynamically --%>
       <tbody>
    <%-- Assuming you have a List<MyObject> called myList containing the data for the table --%>
    <% for (Transaction obj : administrationTransactions) { %>
        <tr>
            <td><input class="form-check-input" type="checkbox"></td>
            <td><%= obj.getDate() %></td>
            <td><%= obj.getIdTransaction() %></td>
            <td><%= obj.getBeneficiaire() %></td>
            <td><%= obj.getMontant() %></td>
            <td><%= obj.getEtat() %></td>
          <td>   <%= obj.getEtat()  %>
       </td>
        </tr>
    <% } %>
</tbody>
    </tbody>




						</table>
                    </div>
                </div>
            </div>
            
            

            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">restaurantTransactions</h6>
                    </div>
                    <div class="table-responsive">
                        <table class="table text-start align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col"><input class="form-check-input" type="checkbox"></th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Transaction</th>
                                    <th scope="col">Bénéficiaire</th>
                                    <th scope="col">Montant</th>
                                    <th scope="col">État</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                             <tbody>
        <%-- Iterate over the transactions list and generate table rows dynamically --%>
       <tbody>
    <%-- Assuming you have a List<MyObject> called myList containing the data for the table --%>
    <% for (Transaction obj : restaurantTransactions) { %>
        <tr>
            <td><input class="form-check-input" type="checkbox"></td>
            <td><%= obj.getDate() %></td>
            <td><%= obj.getIdTransaction() %></td>
            <td><%= obj.getBeneficiaire() %></td>
            <td><%= obj.getMontant() %></td>
            <td><%= obj.getEtat() %></td>
           <td>   <%= obj.getEtat()  %>
       </td>
        </tr>
    <% } %>
</tbody>
    </tbody>




						</table>
                    </div>
                </div>
            </div>
            
            
            
            
            
            
            

            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">departementTransactions</h6>
                    </div>
                    <div class="table-responsive">
                        <table class="table text-start align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col"><input class="form-check-input" type="checkbox"></th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Transaction</th>
                                    <th scope="col">Bénéficiaire</th>
                                    <th scope="col">Montant</th>
                                    <th scope="col">État</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                             <tbody>
        <%-- Iterate over the transactions list and generate table rows dynamically --%>
       <tbody>
    <%-- Assuming you have a List<MyObject> called myList containing the data for the table --%>
    <% for (Transaction obj : departementTransactions) { %>
        <tr>
            <td><input class="form-check-input" type="checkbox"></td>
            <td><%= obj.getDate() %></td>
            <td><%= obj.getIdTransaction() %></td>
            <td><%= obj.getBeneficiaire() %></td>
            <td><%= obj.getMontant() %></td>
            <td><%= obj.getEtat() %></td>
            <td>   <%= obj.getEtat()  %>
       </td>
        </tr>
    <% } %>
</tbody>
    </tbody>




						</table>
                    </div>
                </div>
            </div>
            
            
            
            
            
            

            <!-- Recent Sales Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">foyerTransactions</h6>
                    </div>
                    <div class="table-responsive">
                        <table class="table text-start align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col"><input class="form-check-input" type="checkbox"></th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Transaction</th>
                                    <th scope="col">Bénéficiaire</th>
                                    <th scope="col">Montant</th>
                                    <th scope="col">État</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                             <tbody>
        <%-- Iterate over the transactions list and generate table rows dynamically --%>
       <tbody>
    <%-- Assuming you have a List<MyObject> called myList containing the data for the table --%>
    <% for (Transaction obj : foyerTransactions) { %>
        <tr>
            <td><input class="form-check-input" type="checkbox"></td>
            <td><%= obj.getDate() %></td>
            <td><%= obj.getIdTransaction() %></td>
            <td><%= obj.getBeneficiaire() %></td>
            <td><%= obj.getMontant() %></td>
            <td><%= obj.getEtat() %></td>
         <td>   <%= obj.getEtat()  %>
       </td>
        </tr>
    <% } %>
</tbody>
    </tbody>




						</table>
                    </div>
                </div>
            </div>
            
            
            
            

            <!-- Footer Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light rounded-top p-4">
                    <div class="row">
                        <div class="col-12 col-sm-6 text-center text-sm-start">
                            &copy; <a href="#">Financ'EPT</a>, All Right Reserved. 
                        </div>
                        <!-- <div class="col-12 col-sm-6 text-center text-sm-end"> -->
                            <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                           <!-- Designed By <a href="https://htmlcodex.com">HTML Codex</a> -->
                        </br>
                        <!-- Distributed By <a class="border-bottom" href="https://themewagon.com" target="_blank">ThemeWagon</a> -->
                        </div> 
                    </div>
                </div>
            </div>
            <!-- Footer End -->
        </div>
        <!-- Content End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/chart/chart.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.3.2/chart.min.js"></script>
<script>
window.onpageshow = function(event) {
    if (event.persisted && (window.performance && window.performance.navigation.type === 2)) {
      window.location.reload();
    }
  };


window.history.forward();
function noBack() {
  window.history.forward();
}
(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Sidebar Toggler
    $('.sidebar-toggler').click(function () {
        $('.sidebar, .content').toggleClass("open");
        return false;
    });


    // Progress Bar
    $('.pg-bar').waypoint(function () {
        $('.progress .progress-bar').each(function () {
            $(this).css("width", $(this).attr("aria-valuenow") + '%');
        });
    }, {offset: '80%'});


    // Calender
    $('#calender').datetimepicker({
        inline: true,
        format: 'L'
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        dots: true,
        loop: true,
        nav : false
    });


    // Worldwide Sales Chart
   


    // Salse & Revenue Chart
    var ctx2 = $("#salse-revenue").get(0).getContext("2d");
    var myChart2 = new Chart(ctx2, {
        type: "pie",
        data: {
            labels: ["Foyer",   "Restaurant", "Département", "Administration"],
            datasets: [{
                    label: "Dépenses",
                    data: [<%= data.get(0) %>, <%= data.get(2) %>, <%= data.get(4) %>, <%= data.get(6) %>],

                    backgroundColor: ["rgba(155, 93, 229, 1)","rgba(0, 245, 212, 1)","rgba(0, 187, 249, 1)","rgba(241, 91, 181, 1)"],
                    fill: true
                },
                
            ]
            },
        options: {
            responsive: true
        }
    });
    


    // Single Line Chart
    var ctx3 = $("#line-chart").get(0).getContext("2d");
    var myChart3 = new Chart(ctx3, {
        type: "line",
        data: {
            labels: [50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150],
            datasets: [{
                label: "Etudiant",
                fill: false,
                backgroundColor: "rgba(0, 156, 255, .3)",
                data: [7, 8, 8, 9, 9, 9, 10, 11, 14, 14, 15]
            }]
        },
        options: {
            responsive: true
        }
    });


    // Single Bar Chart
    var ctx4 = $("#bar-chart").get(0).getContext("2d");
    var myChart4 = new Chart(ctx4, {
        type: "bar",
        data: {
            labels: ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi","Samedi"],
            datasets: [{
                backgroundColor: [
                    "rgba(0, 156, 255, .7)",
                    "rgba(0, 156, 255, .6)",
                    "rgba(0, 156, 255, .5)",
                    "rgba(0, 156, 255, .4)",
                    "rgba(0, 156, 255, .3)",
                    "rgba(0, 156, 255, .288)"
                ],
                data: [155, 153, 99, 154, 75,30]
            }]
        },
        options: {
            responsive: true
        }
    });


    // Pie Chart
    var ctx5 = $("#pie-chart").get(0).getContext("2d");
    
    var myChart5 = new Chart(ctx5, {
        type: "pie",
        data: {
            labels: ["Ingrédients et nourriture", "Cuisine", "Matériels", "Transport", "Personnel"],
            datasets: [{
                backgroundColor: [
                    "rgba(0, 156, 255, .7)",
                    "rgba(0, 156, 255, .6)",
                    "rgba(0, 156, 255, .5)",
                    "rgba(0, 156, 255, .4)",
                    "rgba(0, 156, 255, .3)"
                ],
                data: [55, 49, 44, 24, 15]
            }]
        },
        options: {
            responsive: true
        }
    });


    // Doughnut Chart
    var ctx6 = $("#doughnut-chart").get(0).getContext("2d");
    var myChart6 = new Chart(ctx6, {
        type: "doughnut",
        data: {
            labels: ["Italy", "France", "Spain", "USA", "Argentina"],
            datasets: [{
                backgroundColor: [
                    "rgba(0, 156, 255, .7)",
                    "rgba(0, 156, 255, .6)",
                    "rgba(0, 156, 255, .5)",
                    "rgba(0, 156, 255, .4)",
                    "rgba(0, 156, 255, .3)"
                ],
                data: [55, 49, 44, 24, 15]
            }]
        },
        options: {
            responsive: true
        }
    });
    // Données du graphique à barres
    const data = {
        labels: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin'],
        datasets: [{
          label: 'Ventes',
          data: [12, 19, 3, 5, 2, 3],
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgba(255, 99, 132, 1)',
          borderWidth: 1
        }]
      };

      // Options du graphique à barres
      const options = {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      };

      // Créer le graphique à barres
      const ctx = document.getElementById('myChart').getContext('2d');
      const myChart = new Chart(ctx, {
        type: 'bar',
        data: data,
        options: options
      });

     
  

    
})(jQuery);


</script>
    
    
</body>

</html>