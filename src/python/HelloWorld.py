import os
import sys

# Path for spark source folder
os.environ['SPARK_HOME']="C:\\spark-1.6.0"

# Append pyspark  to Python Path
sys.path.append("C:\\spark-1.6.0\\python\\")

try:
    from pyspark import SparkContext
    from pyspark import SparkConf

    print ("Successfully imported Spark Modules")

except ImportError as e:
    print ("Can not import Spark Modules", e)
sys.exit(1)