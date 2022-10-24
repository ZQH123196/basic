
1. 如何查看添加的 interceptor？
    可以使用 HandlerMapping 来获得，自动注入 HandlerMapping，然后 handlerMapping.getHandle(req); 取得对应 request 的 HandlerExecutionChain，handlerExecutionChain.getInterceptorList(); 即可获得。
    dispatchServlerlet 是模板模式，里面有一句 mappedHandler.applyPreHandle(processedRequest, response)，点进去在里面打断点查看 this.interceptorList 即可。
2. 





