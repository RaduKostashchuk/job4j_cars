<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Объявления</title>

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
    <script src="js/index.js?version=8"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <c:if test="${driver != null}">
        <div class="col-2">
            <ul class="nav justify-content-start">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/edit.jsp">
                        Добавить объявление
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-3 offset-7">
            <ul class="nav justify-content-end">
                <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/logout">
                            <c:out value="${driver.name}"/> | Выйти
                        </a>
                </li>
            </ul>
        </div>
        </c:if>
        <c:if test="${driver == null}">
        <div class="col-2 offset-10">
            <ul class="nav justify-content-end">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">
                        Войти
                    </a>
                </li>
            </ul>
        </div>
        </c:if>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="row">
                <div class="col-4">
                    <p class="h5">Объявления</p>
                </div>
                <c:if test="${driver != null}">
                    <div class="col-2 offset-4">
                        <input class="form-check-input" type="checkbox" id="myCheck" onchange="drawTable()">
                        <label class="form-check-label" for="myCheck">
                            Показать мои
                        </label>
                    </div>

                    <div class="col-2">
                        <input class="form-check-input" type="checkbox" id="allCheck" onchange="drawTable()">
                        <label class="form-check-label" for="allCheck">
                            Показать проданные
                        </label>
                    </div>
                </c:if>
                <c:if test="${driver == null}">
                    <div class="col-2 offset-6">
                        <input class="form-check-input" type="checkbox" id="allCheck" onchange="drawTable()">
                        <label class="form-check-label" for="allCheck">
                            Показать проданные
                        </label>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="card-body form-group">
            <table class="table table-bordered">
                <thead class="table-light">
                <tr>
                    <th>Фото</th>
                    <th>Марка</th>
                    <th>Модель</th>
                    <th>Статус</th>
                </tr>
                </thead>
                <tbody id="mainTableBody">
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
