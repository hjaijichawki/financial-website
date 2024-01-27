<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if (request.getAttribute("popupMessage") != null) { %>
    <%-- Inclure le fichier "popup.jsp" --%>
    <%@ include file="popup.jsp" %>
<% } %>

<%@ page import="com.octest.beans.Years" %>
<%@ page import="com.octest.dao.DaoFactory" %>
<%@ page import="com.octest.dao.YearDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.octest.beans.Transaction" %> <!-- Replace with the actual package name of your Transaction class -->
<%@ page import="com.octest.dao.TransactionDao" %> <!-- Replace with the actual package name of your TransactionDao class -->
<%@ page import="com.octest.dao.DaoFactory" %>
<%



//Retrieve data from the database
DaoFactory daoFactory = DaoFactory.getInstance();
YearDao yearDao = daoFactory.getYearDao();

String resultat_ajout_transaction = (String) session.getAttribute("resultat_ajout_transaction");


TransactionDao transactionDao = daoFactory.getTransactionDao();

//Get transactions for the year 2020
List<Integer> years =  yearDao.getAllYears();

//Create a list to store the transaction data
List<Float> data = new ArrayList<Float>();

List<String> yearList = new ArrayList<>();
List<String> totalDepenseList = new ArrayList<>();
List<String> totalTolereList = new ArrayList<>();

try {
    Connection connection = daoFactory.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT year, total_depense, total_tolere FROM years");
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        yearList.add(String.valueOf(resultSet.getInt("year")));
        totalDepenseList.add(String.valueOf(resultSet.getFloat("total_depense")));
        totalTolereList.add(String.valueOf(resultSet.getFloat("total_tolere")));
    }
} catch (SQLException e) {
    e.printStackTrace();
}



%>


<!DOCTYPE html>
<html lang="UTF-8">

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
<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
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
               <h6 class="mb-0">
               <%=  session.getAttribute("username")  %></h6>
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
                                <img src="images\Sans titre(1).png" alt="not found" style="object-fit: cover;width:500px;height:450px">
                            </div>
                            
                        </div>
                    </div>
          <div class="col-sm-12 col-xl-6">
                           <div class="bg-light rounded h-100 p-4">
                            <h6 class="mb-4">Description du profil</h6>
                            <p>Nom:<%=  session.getAttribute("uname")  %></p>
                            <p>Prénom:<%=  session.getAttribute("username")  %></p>
                            <p>Email:<%=  session.getAttribute("email")  %></p>
                           
                        </div>
                           
                    </div>
                    
                </div>
            </div>
         <div class="container" style="display: flex;align-items: center;">
                <div class="form-container" style="flex: 1 ;margin-right: 0;margin-left: 0;">
                    <div class="col-sm-12 col-xl-6" style="margin:20px">
                        <div class="bg-light rounded h-100 p-4">
                            <h6 class="mb-4">Modification du prénom</h6>
                                                        				<form   method="post" action="Home" >
                            
        <div class="form-floating mb-3">
                              <input type="text" class="form-control" id="newname" name="newname" required title="contenir que de lettre " >
                              <label for="montant">Prenom</label>
                              </div>
                                                       <div class="m-n2" style="border-radius:30px;display: flex;justify-content: center; margin-top:50px; margin-bottom:0%">
                              
                              <button type="submit"  name="page" value="newname" class="btn btn-lg btn-primary m-2" style="display: flex;justify-content: center;">Modifier</button>
                              <input type="hidden" name="action" value="submit">
                            
                            
                            
                        </div>
                        </form>

                            
                        </div>
                        
                    </div>
                    <div class="col-sm-12 col-xl-6" style="margin:20px">
                        <div class="bg-light rounded h-100 p-4">
                            <h6 class="mb-4">Modification du nom</h6>
                                                        				<form   method="post" action="Home" >
                            
                            <div class="form-floating mb-3">
                              <input type="text" class="form-control" id="newfullname" name="newfullname" required title="contenir que de lettre " >
                              <label for="montant">Nom</label>
</div>
                         <div class="m-n2" style="border-radius:30px;display: flex;justify-content: center; margin-top:50px; margin-bottom:0%">

                              <button type="submit"  name="page" value="newfullname" class="btn btn-lg btn-primary m-2" style="display: flex;justify-content: center;">Modifier</button>
                              <input type="hidden" name="action" value="submit">
                            
                            
                            
                        </div>
                        
                            </form>
                            
                        </div>
                        
                    </div>
                    <div class="col-sm-12 col-xl-6" style="margin:20px">
                        <div class="bg-light rounded h-100 p-4">
                            <h6 class="mb-4">Modification d'email</h6>
                                                        				<form   method="post" action="Home" >
                            
                                <div class="form-floating mb-3">
                              <input type="email" class="form-control" id="newemail" name="newemail" required  >
                              <label for="montant">Email</label>
                              </div>
                        <div class="m-n2" style="border-radius:30px;display: flex;justify-content: center; margin-top:50px; margin-bottom:0%">
                              
                              <button type="submit"  name="page" value="newemail" class="btn btn-lg btn-primary m-2" style="display: flex;justify-content: center;">Modifier</button>
                              <input type="hidden" name="action" value="submit">
                            
                            
                            
                        </div>
                            </form>
                        </div>
                        
                    </div>
                    <div class="col-sm-12 col-xl-6" style="margin:20px">
                        <div class="bg-light rounded h-100 p-4">
                            <h6 class="mb-4">Modification du mot de passe </h6>
                            				<form   method="post" action="Home" >
                            
                                                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="old" name="old" placeholder="Password" required   title="au minimum 8 caractères, doit contenir des chiffres, des lettres minuscules et majuscules et des caractères spéciaux">
                            <label for="floatingPassword">ancien nouveau  Mot de passe</label>
                        </div>
                            
                            <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="newpsww" name="newpsww" placeholder="Password" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,}$" title="au minimum 8 caractères, doit contenir des chiffres, des lettres minuscules et majuscules et des caractères spéciaux">
                            <label for="floatingPassword">nouveau Mot de passe</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="pswwconf" name="pswwconf" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,}$" placeholder="Password" required>
                            <label for="floatingPassword"> Confirmation du mot de passe</label> 
                            </div>
                         <div class="m-n2" style="border-radius:30px;display: flex;justify-content: center; margin-top:50px; margin-bottom:0%">
                                
                            <button type="submit"  name="page" value="newpsww" class="btn btn-lg btn-primary m-2" style="display: flex;justify-content: center;">Inscrire cet utilisateur</button>
                            <input type="hidden" name="action" value="submit">
                            
                        </div>
                        </form>
                        </div>
                        
                    </div>
              
            
                  </div>            
            <div class="image-container"  style="flex: 1;margin-left: 0; margin: -400px; padding: 0;">
                            <img  src="images\kl.png" alt="not found" style=" height:400px;width:500px;object-fit: cover;">
                            <img  src="images\kjk.png" alt="" style=" height:400px;width:500px;object-fit: cover;">
                            <img  src="images\kkk.png" alt="not found" style=" height:400px;width:500px;object-fit: cover;">
                        </div>
            </div>

              
            
            <!-- Sales Chart End -->


            <!-- Recent Sales Start -->
           
            
           
            <!-- Recent Sales End -->

            


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
            labels: ["Rectorat", "Sisy", "EGES", "Foyer", "Restaurant", "Sysco"],
            datasets: [{
                    label: "Dépenses",
                    data: [15, 30, 55, 45, 70, 65],
                    backgroundColor: "rgba(255, 199, 0, .5)",
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