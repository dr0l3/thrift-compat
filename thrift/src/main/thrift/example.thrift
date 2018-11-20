namespace java com.example.thrift.generated
#@namespace scala com.example.thrift.generated

enum Sex {
    MALE,
    FEMALE,
    OTHER
}

struct User {
    1: string id
    2: string name
    3: i16 age
}

struct Fairy {
    1: string id
    2: string name
    3: i16 age
}

service UserService {
  User getUser(1: string id)
}

service BinaryService {
  string fetchBlob(1: i64 id)
}

service FairyService {
  Fairy getFairy(1: string id)
}

struct GetUserResponse {
    1: optional User user;
}
service Service1 {
    GetUserResponse getUser(1: string id)
}

struct GetUserNameResponse {
    1: optional string name;
}

service Service2 {
    GetUserNameResponse getUserName(1: string id);
    string getString(1: string input);
    string getComplicated(1: string input, 2: i16 number, 3: bool boolean)
}

struct ConvertToFairyResponse {
    1: optional Fairy fairy;
}

service Service3 {
    ConvertToFairyResponse convertToFairy(1: User user)
}