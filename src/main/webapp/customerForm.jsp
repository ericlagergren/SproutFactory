<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id='customer' class='models.Customer' />
<jsp:setProperty name="customer" property="*" />
<%@ page isELIgnored="false" %>

    <style>
        input[type=text], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        select {
            height: 4em;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        div#customerRegistration {
            border-radius: 5px;
            padding: 20px;
            width: 700px;

        }

        form {
            background-color: #f2f2f2;
            padding: 20px;
            width: 100%;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            background-color: #f2f2f2;
            min-width: 740px;
        }

        th {
            font-weight: bold;
            /*background-color: #CCC;*/
            border-bottom: 2px solid #cef;
        }

        th, td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even){background-color: #cef}

    </style>
    <div id="customerRegistration">
    <form action="customerRegistration" method="POST">
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" placeholder="Enter First Name">
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName" placeholder="Enter Last Name">
        <label for="email">Birth Date</label>
        <input type="text" id="email" name="email" placeholder="Enter Email">
        <label for="birth_Date">Birth Date</label>
        <input type="text" id="birth_Date" name="birth_Date" placeholder="Enter Birth Date">

        <input type="submit" value="Submit">
    </form>

    <c:if test="${customer}">
        <div>
            <h3>Last Customer Added</h3>
            <label><jsp:getProperty name="customer" property="firstName"/></label><span>&nbsp;</span>
            <label><jsp:getProperty name="customer" property="lastName"/></label><span>&nbsp;</span>
            <label><jsp:getProperty name="customer" property="email"/></label><span>&nbsp;</span>
            <label><jsp:getProperty name="customer" property="birth_Date"/></label><span>&nbsp;</span>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${not empty sessionScope.customers}">
            <table>
                <tr>
                    <th>ID form tqablel</th>
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
                        <td>${customer.birth_Date}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>No Records found</c:otherwise>
    </c:choose>
</div>
