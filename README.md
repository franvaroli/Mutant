# Mutant

## Introduction

This Repository contains my solution to the assignment of Magneto's Mutant detecting software. It consists of only one method which does the job and two Rest API's, one for using such said method and the other to get the stats.

## How to

The way to use such two said API's is:
1)By sending a String[] in the form of a json to this url: "http://ec2-3-19-64-7.us-east-2.compute.amazonaws.com:8080/mutant/"
2)By calling this address: "http://ec2-3-19-64-7.us-east-2.compute.amazonaws.com:8080/mutant/stats/"

In the first case, the answer will be 202 if the String Array passes the test, 403 if it didn't and 400 if the Array has the wrong format.
In the second case, it will return a json with the stats of the tested Arrays, in this format:
{
    "count_mutant_dna": 1,
    "count_human_dna": 1,
    "ratio": 1
}