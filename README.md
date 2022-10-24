# Step
- Build jar
```
mvnw install
```
- Build docker compose
```
docker compose build
```
- Docker compose start
```
docker compose up -d
```
- SSH to 127.0.0.1:8002
```
username/password:
test:test
```
# RUN for h2c:
```
curl -v --http2 http2-h2c:8080
```
```
*   Trying 10.10.10.3:8080...
* Connected to http2-h2c (10.10.10.3) port 8080 (#0)
> GET / HTTP/1.1
> Host: http2-h2c:8080
> User-Agent: curl/7.81.0
> Accept: */*
> Connection: Upgrade, HTTP2-Settings
> Upgrade: h2c
> HTTP2-Settings: AAMAAABkAAQCAAAAAAIAAAAA
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 101 Switching Protocols
< Connection: Upgrade
< Upgrade: h2c
< Date: Sat, 15 Oct 2022 14:46:17 GMT
* Received 101
* Using HTTP2, server supports multiplexing
* Connection state changed (HTTP/2 confirmed)
* Copying HTTP/2 data in stream buffer to connection buffer after upgrade: len=0
* Connection state changed (MAX_CONCURRENT_STREAMS == 4294967295)!
< HTTP/2 200
< content-type: text/plain;charset=UTF-8
< content-length: 5
< date: Sat, 15 Oct 2022 14:46:17 GMT
<
* Connection #0 to host http2-h2c left intact

// update đến http2 thành công
```

# RUN for h2:
```
curl -v --http2 https://http2-h2 --insecure
curl -iv --http2 https://http2-h2 --insecure --trace /path/to/trace.log
```
```
root@738c7e973b16:/# curl -v --http2 https://http2-h2 --insecure
*   Trying 10.10.10.4:443...
* Connected to http2-h2 (10.10.10.4) port 443 (#0)
* ALPN: offers h2
* ALPN: offers http/1.1
* TLSv1.3 (OUT), TLS handshake, Client hello (1):
* TLSv1.3 (IN), TLS handshake, Server hello (2):
* TLSv1.3 (IN), TLS handshake, Encrypted Extensions (8):
* TLSv1.3 (IN), TLS handshake, Certificate (11):
* TLSv1.3 (IN), TLS handshake, CERT verify (15):
* TLSv1.3 (IN), TLS handshake, Finished (20):
* TLSv1.3 (OUT), TLS change cipher, Change cipher spec (1):
* TLSv1.3 (OUT), TLS handshake, Finished (20):
* SSL connection using TLSv1.3 / TLS_AES_256_GCM_SHA384
* ALPN: server accepted h2
* Server certificate:
*  subject: CN=localhost
*  start date: Oct 15 15:43:16 2022 GMT
*  expire date: Nov 14 15:43:16 2022 GMT
*  issuer: CN=localhost
*  SSL certificate verify result: self signed certificate (18), continuing anyway.
* Using HTTP2, server supports multiplexing
* Copying HTTP/2 data in stream buffer to connection buffer after upgrade: len=0
* h2h3 [:method: GET]
* h2h3 [:path: /]
* h2h3 [:scheme: https]
* h2h3 [:authority: http2-h2]
* h2h3 [user-agent: curl/7.83.1]
* h2h3 [accept: */*]
* Using Stream ID: 1 (easy handle 0x7f7f52b3f1a0)
> GET / HTTP/2
> Host: http2-h2
> user-agent: curl/7.83.1
> accept: */*
>
* TLSv1.3 (IN), TLS handshake, Newsession Ticket (4):
< HTTP/2 200
< content-type: text/plain;charset=UTF-8
< content-length: 5
< date: Mon, 24 Oct 2022 15:41:37 GMT
<
* Connection #0 to host http2-h2 left intact


// dùng http 2 thành công, vì vậy dùng http2
```
# RUN for nang cấp h2 ssl nhưng chỉ hỗ tợ ssl không hỗ tro h2:
```
curl -v --http2 https://http_ssl_not_http2 --insecure
curl -v --http2 https://http_ssl_not_http2 --insecure  --trace /path/to/trace.log

```
```
root@d9f0d6dfb0c4:/# curl -v --http2 https://http_ssl_not_http2 --insecure
*   Trying 10.10.10.6:443...
* Connected to http_ssl_not_http2 (10.10.10.6) port 443 (#0)
* ALPN: offers h2
* ALPN: offers http/1.1
* TLSv1.3 (OUT), TLS handshake, Client hello (1):
* TLSv1.3 (IN), TLS handshake, Server hello (2):
* TLSv1.3 (IN), TLS handshake, Encrypted Extensions (8):
* TLSv1.3 (IN), TLS handshake, Certificate (11):
* TLSv1.3 (IN), TLS handshake, CERT verify (15):
* TLSv1.3 (IN), TLS handshake, Finished (20):
* TLSv1.3 (OUT), TLS change cipher, Change cipher spec (1):
* TLSv1.3 (OUT), TLS handshake, Finished (20):
* SSL connection using TLSv1.3 / TLS_AES_256_GCM_SHA384
* ALPN: server did not agree on a protocol. Uses default.
* Server certificate:
*  subject: CN=localhost
*  start date: Oct 15 15:43:16 2022 GMT
*  expire date: Nov 14 15:43:16 2022 GMT
*  issuer: CN=localhost
*  SSL certificate verify result: self signed certificate (18), continuing anyway.
> GET / HTTP/1.1
> Host: http_ssl_not_http2
> User-Agent: curl/7.83.1
> Accept: */*
>
* TLSv1.3 (IN), TLS handshake, Newsession Ticket (4):
* Mark bundle as not supporting multiuse
< HTTP/1.1 400
< Content-Type: text/html;charset=utf-8
< Content-Language: en
< Content-Length: 435
< Date: Mon, 24 Oct 2022 15:51:28 GMT
< Connection: close
<
* Closing connection 0
* TLSv1.3 (OUT), TLS alert, close notify (256):

// update h2 khong thanh cong vi server khong ho tro h2, vi vay dung http1.1
```

# RUN for http1.1:
```
curl -v --http2 http1.1:8080
```
```

*   Trying 10.10.10.5:8080...
* Connected to http1.1 (10.10.10.5) port 8080 (#0)
> GET / HTTP/1.1
> Host: http1.1:8080
> User-Agent: curl/7.81.0
> Accept: */*
> Connection: Upgrade, HTTP2-Settings
> Upgrade: h2c
> HTTP2-Settings: AAMAAABkAAQCAAAAAAIAAAAA
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 5
< Date: Sat, 15 Oct 2022 16:04:45 GMT
<
* Connection #0 to host http1.1 left intact

/// Không hỗ trợ http2 vì vậy vẫn dùng http1
```
