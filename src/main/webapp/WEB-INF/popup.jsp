<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Financ'EPT Dashboard</title>
    <link href="images/icon.png" rel="icon">
    
</head>
<body>
<!-- Modal -->
<div id="myModal" class="modal" style="width:550px;max-height:340px;margin-left:580px;margin-top:220px; text-align: center; border: 1px solid #ccc; background-color: #f5f5f5; padding: 20px;border-radius:30px" <% if (request.getAttribute("showPopup") == null) { %> style="display: none" <% } %>>
    <!-- Modal content -->
       
        <div class="modal-header" style="width: 100%; height: auto; margin: 0 auto;">
         
            <h2 style="font-family: 'Montserrat', cursive; font-weight: 100; font-size: 40px; text-align: center; color: #666666; margin: 0 0 30px 0;">Notifications</h2>
        </div>
        <div class="modal-body">
            <p class="modal-body" style="font-size: 15px; color: #333;"><%= request.getAttribute("popupMessage") %></p>
        </div>
        <div class="modal-footer">
            <button class="modal-footer" onclick="closePopup()" style="background-color: #010db7;border: none;border-radius: 5px; width: 70px;margin-right:200px;padding-right: 25px;font-size: 20px;color: white;box-shadow: 0px 6px 18px -5px rgb(9, 13, 211);">ok</button>
        </div>
        
           
       
</div>

<script>
// Get the modal
var modal = document.getElementById("myModal");

// If showPopup is not null, display the modal
if ("<%= request.getAttribute("showPopup") %>" !== null) {
    modal.style.display = "block";
}

// When the user clicks on the close button, close the modal
function closePopup() {
    modal.style.display = "none";
}
</script>


</body>
</html>