���-���������� TravelAgencyApp ���������� ������� ����� �����������
��� ������������� �����. ������������ ������� �� URI: localhost:7001/
�������� �� �������� ����������� � ����������. ���� � ������������ ���
��������, �� ����� ���������������� ��� ����� �� ������ "�����������".
����� ����������� ������������ ������ �� �������� �������� ������
������������� �����. � ���� �������� �� ����� �������������� �� �����,
��������� �����������. ���������� ������������ 2 ����: manager � user. 
UI � ���������� ��� ������ ����� ����������.
� ����� testdata.sql ��������� �������� ������ ��� ���������� ��.

Manager �����:
+������������ ������� (� ������� Ajax) ��� ����������� ����� �����;
+���������/�������������/������� ������������� ����;
+������������� ������ � ������������� ���������� ���;

User �����:
+������������ ������� (� ������� Ajax) ��� ����������� ����� �����;
+����������� ����;
+������������� ������ ��������������� �� �����;

�������������� � ������� ���������� (����� �����������):
+Spring MVC;
+Spring Security;
+WebLogic/TomCat;
+PostgreSQL;
+JDBC;
+HTML, CSS, JS;
+Ajax;
+JSP;
+log4j;
+JavaDocs;
+������� � �� ��������� � 3 ���������� �����;
+Liquibase plugin ��� �������� ������ � �� ��� ������ ������ �������;
+3 ������ ��������� ������ ��� ����������� ������ ������������:
	*�� ������� ������� (� ������� JS);
	*�� ������� ������� (������ �� ������������� � �������� ��� ��������);
	*��������� � �� ��� �������� ������������ ���������� email;
	
*** *** *** *** *** *** *** *** *** *** *** *** ***

������ � API:
	�������� header - Content-Type:application/json
1. ����������� (��������� ������).
	� RequestBody �������� ������ � ������������, ��������:
{      
	"email": "user@mail.com",      
	"password": "root" 
}
��������� POST ������ �� http://localhost:7001/api/login

2. ����������� ������������.
	� RequestBody �������� ������ � ����� ������������, ��������:
{
	"email": "user@mail.com",
	"password": "root",
	"firstName": "Andrey",
	"lastName": "Yermolenko",
	"agencyBranchId": "1"
}
��������� POST ������ �� http://localhost:7001/api/sign_up

3. ��������� ������ ��������� �����������.
��������� GET ������ �� http://localhost:7001/api/agencyBranch

4. ��������� ������ ��������.
��������� GET ������ �� http://localhost:7001/api/resort

5. ��������� ������ ������������.
��������� GET ������ �� http://localhost:7001/api/carrier

6. �������� ��� ������������� ����, ������� ������������� ���������� ������.
	� ����������� �� ���� ������������ ������ � ����� ����� ���������� (��������� �������� ������ ������).
	� RequestBody �������� ������ � ����. �����-�� ���������� ����� �� ���� � �������, ��������:
{
    "destination": "Egypt",
    "beginDate": "2020-03-30",
    "endDate": "2020-09-30",
    "minCost": "900",
    "maxCost": "2500",
    "sortedBy": "cost",
    "desc": "false"
}
	���
{
    "destination": "Egypt"
}

	�������� � RequestParameters:
		token = ${tokenValue}
��������� POST ������ �� http://localhost:7001/api/tours

7. ������������� ��� (��� �������� ������������).
	��������� GET ������ �� http://localhost:7001/api/reservationTour
	�������� � RequestParameters ����� ������������ � ID ����:
		token = ${tokenValue}
		id=${idValue}
		
8.��������� ����������������� ����� ��� �������� ������������ (��� ������������).
	��������� GET ������ �� http://localhost:7001/api/reservedTours
	�������� � RequestParameters ����� ����������:
		token = ${tokenValue}		

9. �������� ��� (��� ���������).
	� RequestBody �������� ������ � ����. ��� ���� ����������� � ����������.
	��������:
{
    "destination": "Turkey",
	"beginDate": "2020-08-15",
    "endDate": "2020-08-20",
    "cost": "2000",
    "maxCount": "12",
    "description": "Turkey tour (update)",
	"travelCarrierId": "1",
	"resortId": "2"
}
	�������� � RequestParameters ����� ��������� � ID ����:
		token = ${tokenValue}
		id=${idValue}
��������� POST ������ �� http://localhost:7001/api/updateTour

10. ������� ��� (��� ���������).
	��������� GET ������ �� http://localhost:7001/api/deleteTour
	�������� � RequestParameters ����� ��������� � ID ����:
		token = ${tokenValue}
		id=${idValue}
		
11. �������� ��� (��� ���������).	
	� RequestBody �������� ������ � ����. ��� ���� ����������� � ����������.
	��������:
{
    "destination": "Turkey New",
	"beginDate": "2020-08-15",
    "endDate": "2020-08-20",
    "cost": "2000",
    "maxCount": "12",
    "description": "Turkey tour",
	"travelCarrierId": "1",
	"resortId": "2"
}
	�������� � RequestParameters ����� ���������:
		token = ${tokenValue}
��������� POST ������ �� http://localhost:7001/api/addTour
		
12.��������� ������ ������������� ��� ���� (��� ���������).
	��������� GET ������ �� http://localhost:7001/api/listOfReservedTourUsers
	�������� � RequestParameters ����� ��������� � ID ����:
		token = ${tokenValue}
		id=${idValue}
		