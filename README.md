# Multilevel_Cache_System
This Java application demonstrates the implementation of a dynamic multilevel cache system. The system allows adding multiple cache levels with different eviction policies (LRU or LFU) and supports the insertion and retrieval of key-value pairs across the cache hierarchy.

## Table of Contents
- [Features](#features)
- [Usage](#usage)
  - [Add Cache Level](#add-cache-level)
  - [Insert Key-Value Pair](#insert-key-value-pair)
  - [Retrieve Value by Key](#retrieve-value-by-key)
  - [Remove Cache Level](#remove-cache-level)
  - [Display Cache](#display-cache)
- [How It Works](#how-it-works)
- [Installation](#installation)
  - [LRU Policy](#lru-policy)
  - [LFU Policy](#lfu-policy)

- [License](#license)

## Features
- **Multilevel Cache:** Add multiple cache levels with specified sizes and eviction policies.
- **Eviction Policies:** Supports Least Recently Used (LRU) and Least Frequently Used (LFU) policies.
- **Key-Value Storage:** Store and retrieve key-value pairs from the cache.
- **Cache Display:** Display the current contents of each cache level.
- **Interactive Menu:** A simple command-line interface for interacting with the cache.

## Eviction Policies
- **LRU (Least Recently Used):** Removes the least recently accessed cache entry when the cache reaches its size limit.
- **LFU (Least Frequently Used):** Removes the least frequently used cache entry when the cache reaches its size limit.

## Usage

1. **Add Cache Levels:**
   - Specify the size of the cache level and the eviction policy (LRU or LFU).
   
2. **Insert Key-Value Pairs:**
   - Insert key-value pairs, which are stored in the first cache level (L1).
   
3. **Retrieve Values by Key:**
   - Retrieve values by providing the key. If the value exists in any cache level, it will be returned.
   
4. **Remove Cache Levels:**
   - Remove specific cache levels by their index (e.g., L1, L2, etc.).

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Multilevel_Cache_System
   ```

3. Compile and run the program:

   ```bash
   javac DynamicMultilevelCacheSystem.java
   java DynamicMultilevelCacheSystem
   ```

   ## License
This project is licensed under the MIT License.
