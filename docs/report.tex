\documentclass{article}
\usepackage{graphicx} % Required for inserting images
\usepackage{amsmath}
\usepackage{amsmath}

\title{TSPWR}
\author{Kamil Borkowski}
\date{May 2026}

\begin{document}

\maketitle

\section{Introduction}


\section{Mathematical form of the problem}

\subsection*{Problem Description}
We consider Traveling Salesman Problem With Refueling (TSPWR), which is a variant of the Traveling Salesman Problem where a vehicle must visit all cities exactly once and return to the depot, while respecting a limited fuel capacity. The vehicle consumes fuel proportional to travel distance.

\subsection*{Sets}
We operate on a set of nodes which represent points (cities) visited.
\[
V = \{0,1,\dots,n-1\}
\]
where node $0$ represents the depot.

\subsection*{Parameters}
We consider distance and fuel capacity as parameters.
\[
d_{ij} \ge 0 \quad \text{travel cost / fuel consumption from } i \text{ to } j
\]
\[
L \quad \text{fuel tank capacity}
\]

\subsection*{Decision Variables}

\textbf{Routing variables:}

\[
x_{ij} \in \{0,1\} \quad \forall i \neq j
\]
\[
x_{ij} = 1 \text{ if the route goes directly from } i \text{ to } j
\]

\textbf{Subtour elimination (MTZ variables):}
\[
u_i \in [0, n-1]
\]
In order to prevent taking subtours under consideration, we introduce MTZ (Miller-Tucker-Zemlin). Position variable $u_i$ is assigned to each point in the tour (city). 

\textbf{Fuel variables:}
\[
f_i \in [0, L]
\]
where $f_i$ is the remaining fuel upon arrival at node $i$.

\subsection*{Objective Function}
Minimize total travel cost:
\[
\min \sum_{i \in V} \sum_{j \in V, j \ne i} d_{ij} x_{ij}
\]

\subsection*{Constraints}

\subsubsection*{1. Visit each node exactly once}

Each node has exactly one outgoing edge:
\[
\sum_{j \in V, j \ne i} x_{ij} = 1 \quad \forall i \in V
\]

Each node has exactly one incoming edge:
\[
\sum_{i \in V, i \ne j} x_{ij} = 1 \quad \forall j \in V
\]

\subsubsection*{2. Subtour elimination (MTZ formulation)}

\[
u_i - u_j + n x_{ij} \le n - 1
\quad \forall i,j \in V \setminus \{0\}, i \ne j
\]

This constraint prevents disconnected cycles that do not include the depot by ensuring that if the route goes from node $i$ to node $j$, then $j$ must appear later in the tour than $i$.

\subsubsection*{3. Initial fuel condition}

\[
f_0 = L
\]

The vehicle starts with a full tank at the depot.

\subsubsection*{4. Fuel propagation constraints}

If the arc $(i,j)$ is used, fuel decreases by the travel cost:

\[
f_j = f_i - d_{ij} \quad \text{if } x_{ij} = 1
\]

Linearized using a big-M formulation:

\[
f_j \le f_i - d_{ij} + M(1 - x_{ij}) \quad \forall i \in V,\ \forall j \in V \setminus \{0\}
\]

\[
f_j \ge f_i - d_{ij} - M(1 - x_{ij}) \quad \forall i \in V,\ \forall j \in V \setminus \{0\}
\]

These ensure consistency only when $x_{ij} = 1$.

\subsubsection*{5. Fuel capacity bounds}
\[
0 \le f_i \le L \quad \forall i \in V
\]

The vehicle cannot exceed tank capacity or go below zero fuel.

\subsubsection*{6. Big-M constant}
Big-M is a technique used in MILP to model logical conditions by introducing a large constant $M$. It enables constraints to be active only when a binary variable takes value 1, while becoming non-binding otherwise. In this model, it is used to enforce fuel propagation only along selected arcs.
\[
M = L + \max_{i,j} d_{ij}
\]

This ensures the relaxation is valid but inactive when $x_{ij} = 0$.

\subsection*{Summary}
This model extends the classical TSP by adding a continuous resource dimension (fuel). The problem becomes a Mixed-Integer Linear Program (MILP) with routing decisions and resource feasibility constraints.

\end{document}
