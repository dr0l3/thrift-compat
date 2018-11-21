namespace java com.example.thrift.v1.generated
#@namespace scala com.example.thrift.v1.generated

struct Person {
  1: string name
  2: i64 age
  3: string something
}

enum Color {
    RED
    BLUE
    YELLOW
}

union UserType {
    1: string admin
    2: i64 agent
}

typedef string Name

service Service {

    // Adding input type
//  Person createPerson(1: string name, 2: i64 age)

    //Add normal tyoe
    Person createPersonNormal(1: string name, 2: i64 age)

    //Add required type
    Person createPersonReq(1: string name, 2: i64 age)

    //Add required type
    Person createPersonOptional(1: string name, 2: i64 age)

    //Change type
    Person createPersonChangeType(1: string name, 2: i64 age)

    //Required -> "normal"
    Person createPersonReqToNormal(1: string name, 2: i64 age)

    //Required -> optianal
    Person createPersonReqToOptional(1: string name, 2: optional i64 age)

    //Normal -> Required
    Person createPersonNormalToRequired(1: string name, 2: required i64 age)

    //Normal -> Optional
    Person createPersonNormalToOptional(1: string name, 2: optional i64 age)

    //Optional -> Normal
    Person createPersonOptionalNormal(1: string name, 2: i64 age)

    //Optional -> Required
    Person createPersonOptionalRequired(1: string name, 2: required i64 age)

    // With Enum
    Person createPersonHairColor(1: string name, 2: i64 age, 3: Color color)

    //Typedef -> Normal
    Person createPersonTypedef(1: string name, 2: i64 age)

    Person createPersonTypedefNormal(1: Name name, 2: i64 age)

    //remove required type
    Person createPersonRemoveRequired(1: string name, 2: i64 age, 3: required string something)
    //remove normal type
    Person createPersonRemoveNormal(1: string name, 2: i64 age, 3: string something)
    //remove optional type
    Person createPersonRemoveOptional(1: string name, 2: i64 age, 3: optional string something)

    // Union
    Person createPersonUserType(1: string name, 2: i64 age, 3: UserType userType)
}

// add optional
struct Something {
    1: string a
    2: optional string b
}

// add require
struct Something2 {
    1: string a
    2: required string b
}

//add normal {
struct Something3 {
    1: string a
    2: string b
}

//remove normal
struct Something4 {
    1: string a
}
//remove required
struct Something5 {
    1: string a
}
//remove optional
struct Something6 {
    1: string a
}

//change
struct Something7 {
    1: string a
    2: string b
}

//normal -> optional
struct Something8 {
    1: string a
    2: string b
}
//normal -> required
struct Something9 {
    1: string a
    2: string b
}
//required -> optional
struct Something10 {
    1: string a
    2: required string b
}
//required -> normal
struct Something11 {
    1: string a
    2: required string b
}
//optiona -> normal
struct Something12 {
    1: string a
    2: optional string b
}
//optiona -> required
struct Something13 {
    1: string a
    2: optional string b
}

enum Color2 {
    RED
    YELLOW
    MAGENTA
}

union Meh {
    1: string hello
    2: string woo
}

service Output {
    Something addOptional()
    Something2 addRequired()
    Something3 addNormal()
    Something4 removeNormal()
    Something5 removeRequired()
    Something6 removeOptional()
    Something7 change()
    Something8 normalToOptional()
    Something9 normalToRequired()
    Something10 requiredToOptional()
    Something11 requiredToNormal()
    Something12 optionalToNormal()
    Something13 optionalToRequired()
    Color2 enumChanged()
    Color2 enumRemoved()
    Meh unionChanged()
    Meh unionRemoved()
    Meh unionRenamed()
}
