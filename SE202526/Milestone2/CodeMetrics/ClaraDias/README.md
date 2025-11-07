## Code Metrics Analysis: Clara Dias 67215

## I choose to evaluate 5 classes on 3 diferente code metrics, LoadoutDialog, BlockProducerBuild, VariableReactor, MassDriver and Server launcher being the 5 classes and ATFD (access to foreing data), CBO (coupling bettewn objects) and CMI (Maintainability index) being the 3 diferente code metrics.

### Both ATFD and CBO are standard metrics, while CMI is not, it's defenition can vary. With that being said when we have a high ATFD its normal to have a higher cbo given it creates dependencies, as per afct and cbo both have na inverse correlation with CMI, the higher they are the lower CMI is, given it's maintenence is harder.


### The source of this sample is the plugin "Metric Trees" for Intellij IDEA.

The LoadoutDialog class has low CBO because eventhough it acesses foreing data it passes it on, not keeping it in the class, so it doesn't extablish Strong dependencies. Additionally it extends BaseDialog making it acess data without intruducing extra coupling between objects.

The BlockProducerbuild as the expected correlation bettween the 3 metrics. This class keeps objects from the class recipe in it, creating a bigger ATFD. Given the draw's and shader's interactions the class becomes heavly coupled to external objects.
The CMI is lower given the ATFD and CBO being so high.

The MassDriverBuild has high CBO and a ATFD of 0, because it mainly calls methods that don't access the intern information of other classes only creates reactions, however this makes the CBO higher because the class relies on many external classes through method calls rather than direct field access.

![Graphic](file_2025-11-07_22.44.40[1].png)