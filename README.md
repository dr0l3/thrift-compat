This repo is an attempt to quantify how thrift types can be changed.

Specifically what is interesting to know is whether a change to a type will cause runtime exceptions or whether thrift/scrooge will attempt to do some sort of corrective action.

This is useful when designing API's and answering questions like

- Should this type be a union or just a struct with optional fields?
- Do I want to use an enum or should we stick with a string and parse the enum later?

## Types of mutations to the schema

- Positions
    - input
    - output
- type of type
    - struct
    - enum
    - union
- type of change
    - add "normal"
    - add required
    - add optional
    - change type
    - required -> "normal"
    - required -> optional
    - "normal" -> required
    - "normal" -> optional
    - optional -> required
    - optional -> "normal"
    - add element to co-products
    - remove element from co-product
    - rename element in co-products
    - change type in element in coproduct
    - TypeDef to "regular"
    - "regular" to TypeDef


### Conclusions

- Input
    - You cant change types, ever. It will throw.
    - You cant violate the required keyword (neither by removing the value nor making it optional and sending none). It will throw.
    - With Enums you send the number, not the label. Missing label -> Unknown Value
    - With unions a mutated type will throw. Missing element -> Unknown value. Names do not have semantic meaning.
    - When a "normal" value is missing it gets the default value for its type, if such a value exists (see https://twitter.github.io/scrooge/Semantics.html)
- Output
    - You cant change types, ever. It will throw.
    - You cant violate the required keyword (neither by removing the value nor making it optional and sending none). It will throw.
    - With Enums you send the number, not the label. Missing label -> Unknown Value
    - With unions a mutated type (OR NAME) will throw. Missing element -> Unknown value
    - When a "normal" value is missing it gets the default value for its type, if such a value exists (see https://twitter.github.io/scrooge/Semantics.html)
    
Typedefs doesn't seem to carry any meaning :()
