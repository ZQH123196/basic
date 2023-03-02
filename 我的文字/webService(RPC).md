# webService







webService 其实就是服务器提供了一个远端的服务调用，客户端只需要将方法名传给服务器，服务器就调用自身对应的方法然后返回这个方法运行的结果给客户端，即 RPC(Remote Procedure Call) 远程调用。

说的直白点，webService 就是调用服务器上的一个函数罢了。



这个只是一个简单的概念，所有实现了这个概念的都可以称作 webService，并不局限于所用的语言工具。



它的就类似于：没有什么是添加一层抽象解决不了的事情，如果有，那就在加一层~



webService 跟 MVC、MVVM 这些概念是同层次，都属于纯粹的概念，一种概念往往会有多种实现的框架。



webService 就是一层抽象，这层抽象可以解决客户端跟服务器间跨语言、跨平台的问题，其目的是将运行的压力或者是私密的服务放到服务器，客户端只需要等待返回结果就可以了。



当然了，双方的通讯总是要有约定的，webService 也不例外，这里约定的协议有多种：

* SOAP(Simple Object Access Protocal) 简单对象访问协议
* WSDL(Web Service Definition Language) Web服务描述语言与结构分析
* UDDI(Universal Description Discovery Integration) 统一描述、发现和集成



这个约定都是由服务器向外暴露到网络上，然后各个公司就根据暴露出来的 API 来编写自己的客户端。

Web service一般就是用SOAP协议通过HTTP来调用它，其实他就是一个WSDL文档，客户都可以阅读WSDL文档来用这个Web service。客户根据WSDL描述文档，会生成一个SOAP请求消息。Web service都是放在Web服务器 (如IIS) 后面的，客户生成的SOAP请求会被嵌入在一个HTTP POST请求中，发送到Web服务器来。Web服务器再把这些请求转发给Web service请求处理器。请求处理器的作用在于，解析收到的SOAP请求，调用Web service，然后再生成相应的SOAP应答。Web服务器得到SOAP应答后，会再通过HTTP应答的方式把它送回到客户端。

SOAP简单的理解，就是这样的一个开放协议SOAP=RPC+HTTP+XML：采用HTTP作为底层通讯协议；RPC作为远程调用途径，ＸＭＬ作为数据传送的格式，允许服务提供者和服务客户经过防火墙在INTERNET进行通讯交互。

## 对象的 XML 化

Marshall & unmarshall

