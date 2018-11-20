namespace java com.example.thrift.generated
#@namespace scala com.example.thrift.generated

struct Person {
  1: string name
  2: i64 age
  3: string something
}

service S1 {
    Person createPerson(1: string name, 2: i64 age, 3: required string something)
}