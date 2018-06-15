# MachineLearning
This is my implementations of Machine Learning algorithms in Java.

## DecisionTree
(AKA [Homework #1](http://www.hlt.utdallas.edu/~vgogate/ml/2018s/homeworks.html))
Current state:
- [x] Implement Building Tree
- [x] Implement Information Gain heuristic function
- [x] Implement Variance Impurity Gain heuristic function
- [x] Implement Random heuristic function
- [x] Implement Testing Tree / Calculating Accuracy
- [x] Implement Printing Tree
- [x] Implement Pruning Tree

### Output (at current state):
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

### Compilation (Required as part of homework)
Easy! Tested on `ecj` but should work fine on `javac`. The main class is called [BooleanDecisionTreeRunner.java](https://github.com/arjvik/MachineLearning/blob/master/src/com/arjvik/machinelearning/decisiontree/bool/BooleanDecisionTreeRunner.java).
Usage is as follows (outlined in the homework specification):

```
java com.arjvik.machinelearning.decisiontree.bool.BooleanDecisionTreeRunner <L:integer>
		<K:integer> <training-set:path-to-csv-file>
		<validation-set:path-to-csv-file> <test-set:path-to-csv-file> <to-print:yes|no>
```

## Single Variable Gradient Descent (Linear Regression)

## Ham/Spam Classification
(AKA [Homework #1](http://www.hlt.utdallas.edu/~vgogate/ml/2018s/homeworks.html))
Current state:
- [x] Implement Backend Framework
- [x] Implement Naive Bayes Classification
    - ([NaiveBayesClassifier.java](https://github.com/arjvik/MachineLearning/blob/master/src/com/arjvik/machinelearning/hamspamclassifier/naivebayes/NaiveBayesClassifier.java))
- [ ] Implement NCAP Logistic Regression
- [ ] Implement Perceptropn Algorithm

## K-Nearest-Neighbor Classifier
[KNearestNeighbor.java](https://github.com/arjvik/MachineLearning/blob/master/src/com/arjvik/machinelearning/knearestneighbor/KNearestNeighborClassifier.java)

### Output
```

1-NearestNeighbor classifier:
----------------------------
Without normalization:
Average error: 0.000000e+00
Classification percentage: 100.00%

With normalization:
Average error: 0.000000e+00
Classification percentage: 100.00%

3-NearestNeighbor classifier:
----------------------------
Without normalization:
Average error: 0.000000e+00
Classification percentage: 100.00%

With normalization:
Average error: 0.000000e+00
Classification percentage: 100.00%

5-NearestNeighbor classifier:
----------------------------
Without normalization:
Average error: 0.000000e+00
Classification percentage: 100.00%

With normalization:
Average error: 0.000000e+00
Classification percentage: 100.00%

```

#

