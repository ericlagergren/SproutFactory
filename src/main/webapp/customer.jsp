<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id='customer' class='models.Customer' />
<jsp:setProperty name="customer" property="*" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="container" style="padding-top: 150px;">
    <div class="row">
        <div class="tm-left-right-container">
            <!-- Left column: logo and menu -->
            <div class="tm-blue-bg tm-left-column">
                <nav class="tm-main-nav">
                    <ul class="tm-main-nav-ul">
                        <li class="tm-nav-item">
                            <a href="#addCustomer" class="tm-nav-item-link">Add Customer</a>
                        </li>
                        <li class="tm-nav-item">
                            <a href="#LastCustAdded" class="tm-nav-item-link">Last Customer Added</a>
                        </li>
                        <li class="tm-nav-item">
                            <a href="#customerTable" class="tm-nav-item-link">Customers</a>
                        </li>
                    </ul>
                </nav>
            </div> <!-- Left column: logo and menu -->

            <!-- Right column: content -->
            <div class="tm-right-column">
                <figure>
                    <img src="img/sprouts-01.jpg" alt="Header image" class="img-fluid" style="max-height:696px;max-width: 1344px; ">
                </figure>

                <div class="tm-content-div">
                    <!-- Welcome section -->
                    <section id="addCustomer" class="tm-section">
                        <header>
                            <h2 class="tm-blue-text tm-welcome-title tm-margin-b-45">Customer Registration</h2>
                        </header>
                        <div id="customerRegistration">
                            <form action="customerRegistration" method="POST">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="firstName" placeholder="Enter First Name">
                                <label for="lastName">Last Name</label>
                                <input type="text" id="lastName" name="lastName" placeholder="Enter Last Name">
                                <label for="email">Email Address</label>
                                <input type="text" id="email" name="email" placeholder="Enter Email Address">
                                <label for="birthDate">Birth Date</label>
                                <input type="text" id="birthDate" name="birthDate" placeholder="Enter Your Birth Date">

                                <input type="submit" value="Submit">
                            </form>

                            <br /><hr /><br />

                            <section id="LastCustAdded" class="tm-section">
                                <header>
                                    <h2 class="tm-blue-text tm-welcome-title tm-margin-b-45">Last Customer Added</h2>
                                </header>
                                <c:if test="${not empty sessionScope.customer}">
                                    <div>
                                        <h3>Last Customer Added</h3>
                                        <label><jsp:getProperty name="customer" property="firstName"/></label><span>&nbsp;</span>
                                        <label><jsp:getProperty name="customer" property="lastName"/></label><span>&nbsp;</span>
                                        <label><jsp:getProperty name="customer" property="email"/></label><span>&nbsp;</span>
                                        <label><jsp:getProperty name="customer" property="birthDate"/></label><span>&nbsp;</span>
                                    </div>
                                </c:if>
                            </section>
                            <hr /><br />

                            <section id="customerTable" class="tm-section">
                                <header>
                                    <h2 class="tm-blue-text tm-welcome-title tm-margin-b-45">Customer Table</h2>
                                </header>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.customers}">
                                        <table>
                                            <tr>
                                                <th>ID</th>
                                                <th>First Name</th>
                                                <th>Last Name</th>
                                                <th>Email</th>
                                                <th>Birth Date</th>
                                            </tr>
                                            <c:forEach items="${sessionScope.customers}" var="customer">
                                                <tr>
                                                    <td>${customer.customerID}</td>
                                                    <td>${customer.firstName}</td>
                                                    <td>${customer.lastName}</td>
                                                    <td>${customer.email}</td>
                                                    <td>${customer.birthDate}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:when>
                                    <c:otherwise>No Records found</c:otherwise>
                                </c:choose>
                            </section>
                        </div>
                    </section>

                    <footer>
                        <p class="tm-copyright-p">Copyright &copy; <span class="tm-current-year">2017</span> Sprout Factory | Group 2</p>
                    </footer>
                </div>
            </div> <!-- Right column: content -->
        </div>
    </div> <!-- row -->
</div> <!-- container -->