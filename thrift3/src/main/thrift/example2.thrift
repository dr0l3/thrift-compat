namespace java com.example.thrift.v2.generated
#@namespace scala com.example.thrift.v2.generated

struct Person {
  1: string name
  2: i64 age
  3: string something
}

service Service {
    Person createPerson(1: string name, 2: i64 age, 3: string something)
}