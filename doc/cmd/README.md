# console

### curl
```console
@ECHO OFF

curl --header "Content-Type: application/json" ^
  --request POST ^
  --data "{\"Message\":\"Message #100\"}" ^
  http://127.0.0.1:8080/SEND/PLAY
echo.
pause

curl ^
  --request GET ^
  http://127.0.0.1:8080/RECEIVE/QQQ
echo.
pause
```
