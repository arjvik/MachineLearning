# DecisionTree
(AKA [Homework #1](http://www.hlt.utdallas.edu/~vgogate/ml/2018s/homeworks.html))

## Current state:
- [x] Implement Building Tree
- [x] Implement Information Gain heuristic function
- [x] Implement Variance Impurity Gain heuristic function
- [x] Implement Random heuristic function
- [x] Implement Testing Tree / Calculating Accuracy
- [x] Implement Printing Tree
- [x] Implement Pruning Tree

## Usage as a library
```java
Scenario scenario = new Scenario("X1","X2","X3",... "XN"); // n feature names
DataOutput do1 = new DataOutput(scenario, output, input1, input2, input3, ... inputn); // n inputs, one output
DataOutput do2 = ...;
List<DataOutput> trainingData = new ArrayList<>();
trainingData.add(do1); trainingData.add(do2); ...;
Dataset training = new Dataset(scenario, trainingData);
Dataset validation = ...; // do the same and add validation/testing data
Dataset testing = ...;

BooleanDecisionTree tree = new BooleanDecisionTree(scenario);
Metrics m = tree.train(training);
Metrics m2 = tree.prune(validation);
System.out.printf("Before pruning: %.2f%%%nAfter pruning: %.2f%%%n",
    m.getClassificationPercentage()*100, m2.getClassificationPercentage()*100);

Data d = new Data(scenario, input1, input2, input3, ... inputn); // n inputs
DataOutput do = tree.classify(d);
```

## Homework Output (at current state):
```

Dataset 1:
----------
Information Gain Heuristic:
Pre-pruning accuracy: 75.85%
Pre-pruning validation accuracy: 75.90%
Post-pruning accuracy: 75.85%
Pruning gain: 0.00%

Variance Gain Heuristic:
Pre-pruning accuracy: 76.65%
Pre-pruning validation accuracy: 77.00%
Pruning (round 8973 of 10000) validation new highest accuracy: 78.55%
Post-pruning accuracy: 77.65%
Pruning gain: 1.00%

Random Heuristic:
Pre-pruning accuracy: 52.00%
Pre-pruning validation accuracy: 52.65%
Pruning (round 1 of 10000) validation new highest accuracy: 53.85%
Post-pruning accuracy: 54.80%
Pruning gain: 2.80%


Dataset 2:
----------
Information Gain Heuristic:
Pre-pruning accuracy: 72.33%
Pre-pruning validation accuracy: 77.33%
Pruning (round 1303 of 10000) validation new highest accuracy: 78.83%
Post-pruning accuracy: 73.17%
Pruning gain: 0.83%

Variance Gain Heuristic:
Pre-pruning accuracy: 72.50%
Pre-pruning validation accuracy: 77.33%
Post-pruning accuracy: 72.50%
Pruning gain: 0.00%

Random Heuristic:
Pre-pruning accuracy: 55.00%
Pre-pruning validation accuracy: 56.83%
Post-pruning accuracy: 55.00%
Pruning gain: 0.00%


Net Pruning Gain (All Datasets): 4.63%
```

## Compilation (Required as part of homework)
Easy! Tested on `ecj` but should work fine on `javac`. The main class is called [BooleanDecisionTreeRunner.java](https://github.com/arjvik/MachineLearning/blob/master/src/com/arjvik/machinelearning/decisiontree/bool/BooleanDecisionTreeRunner.java).
Usage is as follows (outlined in the homework specification):

```
java com.arjvik.machinelearning.decisiontree.bool.BooleanDecisionTreeRunner <L:integer>
		<K:integer> <training-set:path-to-csv-file>
		<validation-set:path-to-csv-file> <test-set:path-to-csv-file> <to-print:yes|no>
```