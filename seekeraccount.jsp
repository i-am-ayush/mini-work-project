<%@ page import="com.mysql.cj.Session" %>
<%@ page import="bean.Seeker" %>
<%@ page import="bean.Job" %>
<%@ page import="java.util.List" %>
<%@ page import="service.SeekerService" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>

<jsp:include page="header.jsp"/>

<h3>List of Posted Jobs</h3>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Job Title</th>
        <th>Status</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th colspan="3">Actions</th>
    </tr>
    </thead>
    <tbody>

   <c:forEach items="${requestScope.jobposted}" var="j">
    <tr>
        <td>${j.title}
        </td>
        <td>${j.status}
        </td>
        <td>${j.startDateTime}
        </td>
        <td>${j.endDateTime}
        </td>
        <td>
            <form method="POST" action="editjobdetails.jsp" style="display:inline">
                <input type="text" value="${j.id}" name="id" hidden>
                <button class="btn btn-xs btn-success" type="submit">
                    <i class="glyphicon glyphicon-edit"></i> Edit Job
                </button>
            </form>
        </td>
        <td>
            <form method="POST" action="applicantlist" style="display:inline">
                <input type="text" value="${j.id}" name="id" hidden>
                <button class="btn btn-xs btn-primary" type="submit">
                    <i class="glyphicon glyphicon-list"></i> List Applications
                </button>
            </form>
        </td>
        <td>
            <form method="POST" action="closejob" style="display:inline">
                <input type="text" value="${j.id}" name="id" hidden>
                <button class="btn btn-xs btn-danger" type="button" data-toggle="modal" data-target="#confirmDelete"
                        data-title="Close Job" data-message="Are you sure you want to close this job?">
                    <i class="glyphicon glyphicon-trash"></i> Close Job
                </button>
            </form>
        </td>

    </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
