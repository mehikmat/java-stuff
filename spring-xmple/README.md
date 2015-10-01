###Spring Web MVC framework is built on DispatcherServlet architecture

```
____________________________________________________________________

                         Client 
____________________________________________________________________
    |                                   / \
    |                                    |
    |                                    |
HTTP request                         HTTP Response
    |                                    |
   \ /                                   |
_____________________________________________________________________
                   Dispatcher Servlet
____(1)____________________(2)_______________(3)__________(4)________
   |  / \                 |   / \           |  / \       |  / \
   |   |                  |    |            |   |        |   |
   |   |                  |    |            |   |        |   |
   |   |                  |    |            |   |        |   |
  \ /  |                 \ /   |           \ /  |       \ /  |
________________     ________________  ______________ _________________
Handler Mapping           Handler        View Resolver     View
________________     ________________  ______________ _________________
returns controller    calls appropriate  finds actual  gets model and
name                  service method,       view        renders view
                      sets model and
                      returns logical view
```
