
#include <bits/stdc++.h>
using namespace std;
#define N 4


struct Node
{
	int arr[N][N];
	int tx;
	int ty;
	Node* p;
	int l;
	int cst;
};

int f_cost=0;
int c[] = { 0, -1, 0, 1 };
int r[] = { 1, 0, -1, 0 };



Node* create_Node(int arr[N][N], Node* p, int l, int tx, int ty, int X, int Y)
{
	Node* node = new Node;
	node->p = p;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
        {
            node->arr[i][j]=arr[i][j];
        }
	}

	swap(node->arr[tx][ty], node->arr[X][Y]);
	node->cst = INT_MAX;
	node->l = l;
	node->tx = X;
	node->ty = Y;

	return node;
}

struct cmpr
{
	bool operator()(const Node* left, const Node* right) const
	{
		return (left->cst + left->l) > (right->cst + right->l);
	}
};


int mistile(int s[N][N], int g[N][N])
{
	int m,n;
	int cnt = 0;

	for ( m = 0; m < N; m++)
    {
        for ( n = 0; n < N; n++)
        {
            if (s[m][n] && s[m][n] != g[m][n])
            {
              cnt++;
            }
        }
    }

	return cnt;
}

int manHattan(int s[], int g[])
{
    int m;
    m=0;
    int n;
    n=0;
    int cnt=0;

    while(m<16)
    {
        for(int n=0; n<16; n++)
            {

                if((s[m]!=0) && (g[n] == s[m]))
                {
                    cnt=cnt+abs(n-m);
                }
            }
        m++;
    }

    return cnt;
}


int print(int arr[N][N])
{
	int i,j;
	cout<<"-------------------\n";
	for ( i = 0; i < N; i++)
	{
		cout<<"| ";
		for ( j = 0; j < N; j++)
			cout<<arr[i][j]<<" |";
		cout<<"\n";
	}
	cout<<"-------------------";
}

void show(Node* rt)
{
	if (rt == NULL)
    {
        return;
    }

	show(rt->p);
	print(rt->arr);
	f_cost++;

	cout<<"\n";
}


void solve(int s[N][N], int g[N][N], int tx, int ty)
{

	priority_queue<Node*, std::vector<Node*>, cmpr> ll;

	Node* rt = create_Node(s, NULL, 0, tx, ty, tx, ty);
	rt->cst = mistile(s, g);

	ll.push(rt);

	while (!ll.empty())
	{
		Node* hp = ll.top();

		ll.pop();

		if (hp->cst == 0)
		{
			show(hp);
			return;
		}

		for (int i = 0; i < 4; i++)
		{
			int cx,cy;
			cx=hp->tx + r[i];
			cy=hp->ty + c[i];
			if(cx >= 0 && cx < 4 && cy >= 0 && cy < 4)
            {
                Node* ch = create_Node(hp->arr, hp,hp->l + 1, hp->tx,hp->ty, hp->tx + r[i],hp->ty + c[i]);

				ch->cst = mistile(ch->arr, g);

				ll.push(ch);
            }

		}
	}
}

void solve2(int s[N][N], int g[N][N], int tx, int ty)
{

	priority_queue<Node*, std::vector<Node*>, cmpr> ll;

	Node* rt = create_Node(s, NULL, 0, tx, ty, tx, ty);

	int Count = manHattan((int*)s, (int*)g);
	rt->cst = Count;

	ll.push(rt);

	while (!ll.empty())
	{
		Node* hp = ll.top();

		ll.pop();

		if (hp->cst == 0)
		{
			show(hp);
			return;
		}

		for (int i = 0; i < 4; i++)
		{
			int cx,cy;
			cx=hp->tx + r[i];
			cy=hp->ty + c[i];
			if(cx >= 0 && cx < 4 && cy >= 0 && cy < 4)
            {
                Node* ch = create_Node(hp->arr, hp, hp->l + 1, hp->tx, hp->ty, hp->tx + r[i], hp->ty + c[i]);

				int Count2 = manHattan((int*)ch->arr, (int*)g);
	            ch->cst = Count2;

				ll.push(ch);
            }

		}
	}
}


int inv(int c[])
{
    int cnt = 0;
    for (int i = 0; i < N * N - 1; i++)
    {
        for (int j = i + 1; j < N * N; j++)
        {
            if ( c[i] && c[j] && c[i] > c[j])
            {
               cnt++;
            }
        }
    }
    return cnt;
}

bool solvable(int mat[N][N],int f)
{
    int y = inv((int*)mat);

    if (N & 1)
        return !(y & 1);

    else
    {
        int crd = f;
        if (crd & 1)
            return !(y & 1);
        else
            return y & 1;
    }
}


int main()
{

	int a,b,x;
	int start[N][N] =
	{
		{1, 2, 3, 4},
		{9, 5, 10, 6},
		{13, 7, 12, 8},
		{14, 0, 11, 15}
	};


	int goal[N][N] =
	{
		{1, 2, 3, 4},
		{5, 6, 7, 8},
		{9, 10, 11, 12},
		{13, 14, 15, 0}
	};
	for (int i = N - 1; i >= 0; i--)
        for (int j = N - 1; j >= 0; j--)
            if (start[i][j] == 0)
            {
                a=i;
                b=j;
            }
    x=N-a;

	if(solvable(start,x))
    {
        //printf("Solvable.\n\n");
        cout<<"Solvable.\n\n";
        //cout<<"Misplaced Tiles:\n"<<endl;
        //solve(start, goal, a, b);
        cout<<"Manhattan:\n"<<endl;
        solve2(start, goal, a, b);

    }
    else
    {
        //printf("Not Solvable.\n");
        cout<<"Not Solvable.\n\n";
    }

    cout<<"\n Path cost: "<<f_cost<<endl;

	return 0;
}
