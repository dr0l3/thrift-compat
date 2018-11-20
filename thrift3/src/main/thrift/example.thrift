namespace java com.example.thrift.v1.generated
#@namespace scala com.example.thrift.v1.generated

struct Person {
  1: string name
  2: i64 age
}

union Musk {
  1: string meh
  2: bool muh
}

service Service {
    Person createPerson(1: string name, 2: i64 age)
    Musk getMusk()
}