# IFC.JAVA
Java class library for IFC2x3_TC1 serialization into STEP files.  
The library is not complete, classes were generated automatically and are mostly
broken, I fixed only the ones I needed to use. A quick way to spot a broken
class is looking at its fields, if none are annotated then it's probably broken.

To serialize an `IfcProject`, you should pass it to
`Serialize.serialize(IfcProject project)`, this will generate the DATA section
of the IFC STEP file.
 
Some notes on the implementation:
+ classes having constructors with lots of parameters that can be null contain
Builders, you'll probably want to use those;
+ interface `IfcDefinedType` is not part of the IFC specification, its only
purpose is to be implemented by types which can be serialized directly (called
Defined Types in the IFC specification);
+ abstract class `IfcEntity` is not part of the IFC specification, its only
purpose is to be extended by IFC Entities, which cannot be serialized
directly because they have attributes, which reference other entities or
Defined Types;
+ attributes which are derived or part of inverse relationships are mostly
ignored (commented) at the moment, because they're not needed for the
serialization of the entities they belong to. However, in some cases they're
needed to check if constraints from the specification are met, or to
contain references to other entities which must be serialized after the
current entity: in these cases they're present and annotated with
`@InverseAttribute`. For example, the inverse relationship `isDecomposedBy` in
`IfcProject` contains the entities which compose the `IfcProject`, so all the
entities referenced by that inverse relationship must be serialized after
`IfcProject`.