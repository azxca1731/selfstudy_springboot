import React, { useState, useEffect } from "react";
import styled from "styled-components";
import Post from "./Post";
import Input from "./Input";
import axios from "axios";

const AppDiv = styled.div`
	padding: 30px;
	min-height: 100vh;
	background-color: skyblue;
	width: 100vw;
`;

function App() {
	const [appState, setState] = useState({
		input: "",
		loading: false,
		posts: []
	});

	// useEffect로 ComponentDidMount처럼 변경
	// useEffect(() => {
	// 	axios
	// 		.get("http://localhost:8080/posts")
	// 		.then(({ data }) =>
	// 			setState({ input: "", loading: false, posts: data })
	// 		);
	// }, []);

	const getPosts = async () => {
		setState({
			...appState,
			loading: true
		});
		try {
			const value = await axios.get("http://localhost:8080/posts");
			setState({ input: "", loading: false, posts: value.data });
		} catch (err) {
			console.log(err);
		}
	};
	return (
		<AppDiv>
			<h2 className="text-primary text-center mb-4">Timeliner!</h2>
			<Input
				changeState={setState}
				getPosts={getPosts}
				appState={appState}
			/>
			{appState.posts.map(({ idx }) => (
				<Post key={idx} />
			))}
		</AppDiv>
	);
}

export default App;
