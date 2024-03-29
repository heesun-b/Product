<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>상품 수정 페이지</title>

            <style>
                .flex_box {
                    display: flex;
                }
            </style>

        </head>

        <body>
            <ul>
                <li>
                    <a href="/">홈</a>
                </li>
                <li>
                    <a href="/product/addForm">상품등록</a>
                </li>
            </ul>
            <h1>상품 수정 페이지</h1>
            <hr>
            <form action="/product/${productId.id}/update" method="post">
                <table border="1">
                    <tr>
                        <th>번호</th>
                        <th>상품명</th>
                        <th>가격</th>
                        <th>재고</th>
                        <th>등록일</th>
                    </tr>
                    <tr>
                        <td><input type="text" value="${productId.id}" readonly size="4"></td>
                        <td><input type="text" value="${productId.name}" name="name" size="10"></td>
                        <td><input type="text" value="${productId.price}" name="price" size="10"></td>
                        <td><input type="text" value="${productId.qty}" name="qty" size="4"></td>
                        <td><input type="text" value="${productId.createdAt}" readonly></td>
                    </tr>
                </table>
                <button type="submit"> 수정완료 </button>
            </form>
        </body>

        </html>