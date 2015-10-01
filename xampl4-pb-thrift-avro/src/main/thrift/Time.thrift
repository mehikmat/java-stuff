# time.thrift
namespace java xample.generated

typedef i64 Timestamp

service TimeServer {
   Timestamp time()
}