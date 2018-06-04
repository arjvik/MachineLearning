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

Output (at current state):
```

Dataset 1:
----------
Information Gain Heuristic:
Pre-pruning accuracy: 75.85%
Pre-pruning validation accuracy: 75.90%
Pruning (round 2225 of 10000) validation new highest accuracy: 77.00%
Post-pruning accuracy: 76.30%
Pruning gain: -0.45%

Variance Gain Heuristic:
Pre-pruning accuracy: 76.65%
Pre-pruning validation accuracy: 77.00%
Post-pruning accuracy: 76.65%
Pruning gain: 0.00%

Random Heuristic:
Pre-pruning accuracy: 48.30%
Pre-pruning validation accuracy: 48.50%
Post-pruning accuracy: 48.30%
Pruning gain: 0.00%


Dataset 2:
----------
Information Gain Heuristic:
Pre-pruning accuracy: 72.33%
Pre-pruning validation accuracy: 77.33%
Post-pruning accuracy: 72.33%
Pruning gain: 0.00%

Variance Gain Heuristic:
Pre-pruning accuracy: 72.50%
Pre-pruning validation accuracy: 77.33%
Pruning (round 187 of 10000) validation new highest accuracy: 78.50%
Post-pruning accuracy: 74.00%
Pruning gain: -1.50%

Random Heuristic:
Pre-pruning accuracy: 57.17%
Pre-pruning validation accuracy: 55.00%
Pruning (round 1 of 10000) validation new highest accuracy: 58.83%
Post-pruning accuracy: 54.00%
Pruning gain: 3.17%


Net Pruning Gain (All Datasets): 1.22%

```

### Pruning Accuracy
- Pruning seems to decrease accuracy most of the time.
- The average accuracy gain due to pruning is `-0.83%`.
- This was calculated by running the algorithm 1000 times, with L and K values of 1,000 and 100 respectively.
- Pruning rarely gives any gains, and when it does, the gains are less than 1%.
- This is most likely caused by small gains in validation accuracy that overfit it to the validation dataset.
   - In order to offset this, I required the validation gains during pruning to be at least 1%. This made no difference.

### Compilation (Required as part of homework)
Easy! Tested on `ecj` but should work fine on `javac`. The main class is called, you guessed it, `Main.java`.