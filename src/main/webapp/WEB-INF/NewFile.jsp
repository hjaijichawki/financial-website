<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Log In-Financ'EPT</title>
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

   
        <!-- Spinner End -->


        <!-- Sign In Start -->
        <form   method="post" action="Home" >
        
            
        <div class="container-fluid">
            
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-6" style="margin-left: -25px;">
                    <img src="images/first.png" alt="Left Image" width="100%" >
                </div>
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4" style="margin-right: -50px;">
                  
                  <div class="bg-light rounded p-6 p-sm-5 my-4 mx-3">
                    <div class="logo" style="position: relative;left: -73px;top: 30px;" >
                        <img src="images/logo.png"  >    
                    </div>
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <a href="index.html" class="">
                                <h5 class="text-primary"> Financ'EPT</h5>
                            </a>
                            <h5>Se Connecter</h5>
                        </div>
                         <% if (session.getAttribute("msgconnection") != null) { %>
                                  <p>  <%= session.getAttribute("msgconnection") %></p>
<% } %>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="username" name="username" placeholder="name@example.com" >
                            <label for="floatingInput">Adresse E-mail</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="password" name="password"  placeholder="Password" >
                            <label for="floatingPassword">Mot de passe</label>
                        </div>
                        
  							

                        <button type="submit"  name="page" value="SeConnecter" class="btn btn-primary py-3 w-100 mb-4">Se Connecter</button>
                         <input type="hidden" name="action" value="submit">
                         <button type="submit" name="page" value="motedepasseoublie" style="border:0cm;color:dark yellow ">Mot de passe oublié</button>
 							 <input type="hidden" name="action" value="submit">
					 
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign In End -->
        </form>
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
    <script >

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


