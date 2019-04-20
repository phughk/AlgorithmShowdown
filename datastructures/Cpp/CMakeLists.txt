cmake_minimum_required(VERSION 3.10.2)
project(DataStructures)

include_directories(${MY_SOURCE_DIR}/src)

set(CMAKE_CXX_STANDARD 17)
# set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ")

add_executable(Showcase showcase.cpp)