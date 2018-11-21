## Intellij

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
    - You cant violate the required keyword (neither by removing the value nor making it optional). It will throw.
    - With Enums you send the number, not the label. Missing label -> Unknown Value
    - With unions a mutated type (OR NAME) will throw. Missing element -> Unknown value
    - When a "normal" value is missing it gets the default value for its type, if such a value exists
- Output
    - You cant change types, ever. It will throw.
    - You cant violate the required keyword (neither by removing the value nor making it optional). It will throw.
    - With Enums you send the number, not the label. Missing label -> Unknown Value
    - With unions a mutated type (OR NAME) will throw. Missing element -> Unknown value
    - When a "normal" value is missing it gets the default value for its type, if such a value exists