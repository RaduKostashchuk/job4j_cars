<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Объявления</title>

    <link href="css/index.css" rel="stylesheet" />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
    <script>
        let driverId = -1;
        <c:if test="${driver != null}">
            driverId = <c:out value="${driver.id}"/>;
        </c:if>

        let context = "http://" + "<%=request.getServerName()%>"
            + ":" + "<%=request.getServerPort()%>"
            + "<%=request.getServletContext().getContextPath()%>";
    </script>
    <script src="js/lib.js?version=3"></script>
    <script src="js/index.js?version=3"></script>
</head>
<body>
    <div class="my-navbar bg-info">
        <div class="row">
            <div class="col-4">
                <p class="h5 text-center m-2">Autosale.ru</p>
            </div>
            <c:if test="${driver != null}">
            <div class="col-2 offset-4">
                <a class="btn btn-primary m-1" href="<%=request.getContextPath()%>/edit.jsp">
                    Добавить объявление
                </a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary m-1" href="<%=request.getContextPath()%>/logout">
                    <c:out value="${driver.name}"/> | Выйти
                </a>
            </div>
            </c:if>
            <c:if test="${driver == null}">
            <div class="col-2 offset-4">
                <a class="btn btn-primary disabled m-1" href="<%=request.getContextPath()%>/edit.jsp" >
                    Добавить объявление
                </a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary m-1" href="<%=request.getContextPath()%>/login.jsp">
                    Войти
                </a>
            </div>
            </c:if>
        </div>
    </div>

    <div class="sidebar">
        <ul class="list-group">
            <li class="list-group-item">
                <p class="h6 text-center m-1">Фильтры</p>
            </li>
            <li class="list-group-item">
                <c:if test="${driver != null}">
                <input class="form-check-input" type="checkbox" id="myCheck" onchange="queryBack()">
                </c:if>
                <c:if test="${driver == null}">
                <input class="form-check-input" type="checkbox" id="myCheck" onchange="queryBack()" disabled>
                </c:if>
                <label class="form-check-label" for="myCheck">
                    Показать мои
                </label>
            </li>
            <li class="list-group-item">
                <input class="form-check-input" type="checkbox" id="allCheck" onchange="queryBack()">
                <label class="form-check-label" for="allCheck">
                    Отображать проданные
                </label>
            </li>
            <li class="list-group-item">
                <input class="form-check-input" type="checkbox" id="photoCheck" onchange="queryBack()">
                <label class="form-check-label" for="photoCheck">
                    С фото
                </label>
            </li>
            <li class="list-group-item">
                <input class="form-check-input" type="checkbox" id="lastDayCheck" onchange="queryBack()">
                <label class="form-check-label" for="lastDayCheck">
                    За последние сутки
                </label>
            </li>
            <li class="list-group-item">
                <select class="custom-select" id="brandSelect" name="brand" onchange="queryBack()">
                    <option value="0" selected>Все марки</option>
                </select>
            </li>
        </ul>
    </div>
    <div class="content">
        <div class="row">
            <div class="col-10">
                <table class="table table-borderless table-striped table-hover">
                    <tbody id="mainTableBody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
