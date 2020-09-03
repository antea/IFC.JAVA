# IFC.JAVA
Library for IFC2x3_TC1 serialization into STEP files.  
The library is not complete, classes were generated automatically and are mostly
broken, I fixed only the ones I needed to use. A quick way to spot a broken
Entity is looking at its fields, if none are annotated then it's probably
broken; broken Defined Types and Enumerations don't implement DefinedType.

To serialize an `IfcProject`, you should pass it to
`Serializer.serialize(Header, IfcProject, String)`.
 
Some notes on the implementation:
+ classes having constructors with lots of parameters that can be null contain
Builders, you'll probably want to use those;
+ interface `DefinedType` is implemented by all Defined Types and Enumerations;
+ abstract class `Entity` is extended by all Entities;
+ attributes which are derived or part of inverse relationships are mostly
ignored (commented) at the moment, because they're not needed for the
serialization of the entities they belong to. However, in some cases they're
needed to check if constraints from the specification are met, or to
contain references to other entities which must be serialized after the
current entity: in these cases they're present and annotated with
`@InverseRelationship`. For example, the inverse relationship `isDecomposedBy`
in `IfcProject` contains the entities which compose the `IfcProject`, so all the
entities referenced by that inverse relationship must be serialized after
`IfcProject`.

##Contributing

To fix broken Entities and Defined Types:
+ extend `Entity` or implement `DefinedType`, depending on what the class
represents;
+ if you're fixing an Entity make sure that the type of the attributes is the
one defined in the IFC specification and annotate them with `@Attribute`; keep
in mind that derived attributes are not supposed to be serialized and so
generally there's no need to have them in the Java class, however sometimes they
might be useful in `equals()` and `hashCode()` (for example, in
`IfcAxis2Placement3D` derived attribute `P` is used in `equals()` and
`hashCode()` to correctly compare instances of the class that have different
`Axis` and `RefDirection` but actually represent the same three axes);
+ if any of the attributes are Select Types, make sure that the corresponding
interface in `buildingsmart.ifc` is implemented by all the types or entities
listed in the Select Type's specification;
+ if you're fixing an Entity which has any inverse relationships, check if the
referenced Entity would always be serialized even if the current class did not
contain a reference to it (for example, let's say we have only one `IfcPerson`
and only one `IfcPersonAndOrganization` referenced in our entire `IfcProject`.
Entity `IfcPerson` has an inverse relationship that references each
`IfcPersonAndOrganization` the person is in. However, there's no need to have
that inverse relationship in the `IfcPerson` Java class, since `IfcProject`
contains reference `owningUser` to the `IfcPersonAndOrganization`). If that's
the case, you can avoid annotating the related field with
`@InverseRelationship`. Otherwise, you should add the annotation, and make sure
that the value of the field is updated when the referenced Entity gets a
reference to the current Entity (for example, when an `IfcRelDecomposes` is
created, the inverse relationships of `relatingObject` and `relatedObjects` are
updated);
+ add a constructor that checks for null values when attributes are not
optional, you should use Lombok's `@NonNull`;
+ in the constructor, add checks for all the constraints in the EXPRESS
specification (e.g. minimum and maximum cardinality of Sets and Lists, WHERE
clauses, etc.) and throw an `IllegalArgumentException` when they're not met. If
inverse relationships are needed to check those constraints, add them and
annotate them;
+ if some function is called in the EXPRESS specification, check if it's already
implemented in `buildingsmart.util.Functions`, otherwise add it in that class;
+ implement `equals()`, `hashCode()` and `toString()` (you should use Lombok
annotations), calling the superclass' methods if they exist. Do not check
inverse relationships in any of these methods, or you'll probably get infinite
recursion when calling them;
+ if a class has any derived attributes that are also present in its superclass
and weren't derived attributes in the superclass, annotate the class with
`@DerivedAttributes` and specify the names of attributes from the superclass
that became derived in the class;
+ if an entity has attributes that are EXPRESS simple data types, use the
appropriate DefinedType instead of Java primitives, otherwise they won't be
serialized.